package com.shailendra.ecom.service;

import com.shailendra.ecom.io.ProductRequest;
import com.shailendra.ecom.io.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

   /**
    * To add new category
    * @param request from the user
    * @return id, categoryId,  name, image, created-date, updated-date,
    */
   ProductResponse add(ProductRequest request, MultipartFile file);


   /**
    * To show all the product to user
    * @return list of all product
    */
   List<ProductResponse> read();

   /**
    * Delete product form data base
    * @param productId need
    */
   void delete(String productId);


}
