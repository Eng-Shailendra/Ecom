package com.shailendra.ecom.entity;

import com.shailendra.ecom.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false )
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String country;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER; //

    private String verifyOtp;
    private long verifyOtpExpireAt;
    private boolean isAccountVerified;
    private long resetOtpExpireAt;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

}
