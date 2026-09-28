package com.shailendra.ecom.repository;

import com.shailendra.ecom.entity.ProductEntity;
import com.shailendra.ecom.io.ProductRequest;
import com.shailendra.ecom.io.ProductResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

Optional<ProductEntity> findById(String id);
Boolean existsById(String id);


}
