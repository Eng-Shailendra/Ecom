package com.shailendra.ecom;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    private List<User> userList = new ArrayList<>();
    private Long nextId = 1L;

    public List<User> fetchAllUser(){
        return userList;
    }

    public Optional<User> fetchUser(long id){

//        for (User user : userList){
//            if(user.getId() == id){
//                return  user;
//            }
//        }
//        return  null;

       return userList.stream().filter((user -> user.getId() == id )).findFirst();
    }

    public List<User> addAllUser( User user){
        user.setId(nextId++);
        userList.add(user);
        return userList;
    }

    public  boolean updateUserDetails(User updatedUser, long id){
         return userList.stream().filter((user -> user.getId() == id )).findFirst().map( existingUser ->{
                     existingUser.setFirstname(updatedUser.getFirstname()) ;
                     existingUser.setLastname(updatedUser.getLastname());
            return true;
         }).orElse(false);
    }

}
