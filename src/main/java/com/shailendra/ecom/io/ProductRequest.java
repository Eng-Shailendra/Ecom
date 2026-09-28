package com.shailendra.ecom.io;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductRequest {
    private String productName;
    private String description;


}
