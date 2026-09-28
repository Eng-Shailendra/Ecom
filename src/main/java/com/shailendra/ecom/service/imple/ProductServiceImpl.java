package com.shailendra.ecom.service.imple;

import com.shailendra.ecom.entity.ProductEntity;
import com.shailendra.ecom.io.ProductRequest;
import com.shailendra.ecom.io.ProductResponse;
import com.shailendra.ecom.repository.ProductRepository;
import com.shailendra.ecom.service.FileUploadService;
import com.shailendra.ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final FileUploadService fileUploadService;

    @Override
    public ProductResponse add(ProductRequest request, MultipartFile file) {

        String imageUrl = fileUploadService.uploadFile(file);
       ProductEntity newProduct =  convertToEntity(request);
        newProduct.setImageUrl(imageUrl);
       newProduct=  productRepository.save(newProduct);
       return  convertToResponse(newProduct);
    }

    @Override
    public List<ProductResponse> read() {
        return productRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String productId) {
        ProductEntity existingCategory = productRepository.findById(productId)
                .orElseThrow(()-> new RuntimeException("Product not found"+productId));
        fileUploadService.deleteFile(existingCategory.getImageUrl());
        productRepository.delete(existingCategory);
    }

    private ProductResponse convertToResponse(ProductEntity newCategory) {
        return  ProductResponse.builder().productId(newCategory.getProductId())
                .productName(newCategory.getProductName())
                .description(newCategory.getDescription())
                .imageUrl(newCategory.getImageUrl())
                .createdDate(newCategory.getCreatedDate())
                .updatedDate(newCategory.getUpdatedDate())
                .build();
    }

    private ProductEntity convertToEntity(ProductRequest request) {
       return ProductEntity.builder()
                .productId(UUID.randomUUID().toString())
                .productName(request.getProductName())
                .description(request.getDescription())
                .build();
    }
}
