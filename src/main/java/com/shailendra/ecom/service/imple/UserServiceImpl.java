package com.shailendra.ecom.service.imple;

import com.shailendra.ecom.entity.User;
import com.shailendra.ecom.exceptionHandler.EmailAlreadyExistsException;
import com.shailendra.ecom.exceptionHandler.UserNameAlreadyExistsException;
import com.shailendra.ecom.io.LoginRequest;
import com.shailendra.ecom.io.LoginResponse;
import com.shailendra.ecom.io.RegisterRequest;
import com.shailendra.ecom.io.RegisterResponse;
import com.shailendra.ecom.repository.UserRepository;
import com.shailendra.ecom.service.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class UserServiceImpl implements UserServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;




    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
            if(registerRequest == null){
                throw new IllegalArgumentException("RegisterRequest cannot be null");
            }
            String email = registerRequest.getEmail().trim().toLowerCase(Locale.ROOT);
            String username = registerRequest.getUsername().trim();
            if(userRepository.existsByEmail(email)) {
                throw new EmailAlreadyExistsException("Email already exist  : " + email);
            }
            if(userRepository.existsByUsername(username)) {
                throw new UserNameAlreadyExistsException("User name already exist : "+ username);
            }
            String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
            User user = User.builder()
                    .username(username)
                    .email(email)
                    .password(encodedPassword)
                    .fullName(registerRequest.getFullname())
                    .build();
           User savedUser = userRepository.save(user);

           return new RegisterResponse(
                   savedUser.getId(),
                   savedUser.getEmail(),
                   savedUser.getUsername(),
                   savedUser.getFullName(),
                   savedUser.getCreatedAt(),
                   savedUser.getUpdatedAt()
                   );
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        return null;
    }
}
