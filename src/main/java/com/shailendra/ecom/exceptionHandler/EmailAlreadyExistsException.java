package com.shailendra.ecom.exceptionHandler;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String email){
            super("Email Already Registered"+ email);
    }
}
