package com.shailendra.ecom.io;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ProductResponse {
    private String productId;
    private String productName;
    private String description;
    private String imageUrl;
    private Timestamp createdDate;
    private Timestamp updatedDate;

}
