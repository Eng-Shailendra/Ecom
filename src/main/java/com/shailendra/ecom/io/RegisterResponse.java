package com.shailendra.ecom.io;

import lombok.*;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterResponse {
    private long id;
    private String email;
    private String username;
    private String fullname;
    private Timestamp createdAt;
    private Timestamp updatedAt;



}
