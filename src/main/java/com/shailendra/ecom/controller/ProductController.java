package com.shailendra.ecom.controller;

import com.shailendra.ecom.io.ProductRequest;
import com.shailendra.ecom.io.ProductResponse;
import com.shailendra.ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import tools.jackson.databind.ObjectMapper;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(@RequestPart("product") String productString,
                                      @RequestPart("file") MultipartFile file) {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductRequest request;
        try {
           request =  objectMapper.readValue(productString, ProductRequest.class);
            return productService.add(request, file);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Exception occurred while parsing the json " +
                    "Object"+e.getMessage());
        }

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getProducts() {
        return productService.read();
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable String productId){
        productService.delete(productId);
    }
}
