package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

/**
 * @author Linh
 * @project demo
 */
public interface UserService extends FieldValueExists {
    
    User saveUser(User user);
    
    boolean checkUserByIdAndPassword(Long id, String password);
    
    User findUserById(Long id);
    
    List<User> findListUser();
}
