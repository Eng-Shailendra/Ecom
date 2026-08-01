package com.shailendra.ecom;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {


    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping
    public List<User> getAllUser(){
        return userServices.fetchAllUser();
    }
    
    @GetMapping("/{id}")
    public  ResponseEntity<User> getUser(@PathVariable long id){
//        return new ResponseEntity<>( userServices.fetchUser(id), HttpStatus.OK);
        return  userServices.fetchUser(id).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user){
         userServices.addAllUser(user);
        return ResponseEntity.ok("User created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateUser(@RequestBody User updatedUser, @PathVariable long id){
        boolean updated = userServices.updateUserDetails(updatedUser, id);
       if(updated){
           return ResponseEntity.ok("User updated successfully");
       }
       return  ResponseEntity.notFound().build();
    }

}
