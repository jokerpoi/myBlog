package com.poi.service;

import com.poi.entity.User;
import com.poi.jpa.UserJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserJpa userJpa;

    public List<User> findAll(){
        return userJpa.findAll();
    }

    public User getUserById(int id){
        User user = null;
        Optional<User> userOpt = userJpa.findById(new Integer(id));
        if(!Optional.empty().equals(userOpt)){
            user = userOpt.get();
        }
        return user;
    }
}
