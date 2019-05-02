package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author Linh
 * @project demo
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Checks whether or not a given value exists for a given field
     *
     * @param value     The value to check for
     * @param fieldName The name of the field for which to check if the value exists
     * @return True if the value exists for the field; false otherwise
     * @throws UnsupportedOperationException
     */
    @Override
    public boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException {
        Assert.notNull(fieldName);
        
        if (!fieldName.equals("username") && !fieldName.equals("email")) {
            throw new UnsupportedOperationException("Field name not supported");
        }
        
        if (value == null) {
            return false;
        }
        if (fieldName.equals("username")) {
            return this.userRepository.existsUserByUsername(value.toString());
        } else {
            return this.userRepository.existsUserByEmail(value.toString());
        }
    }
    
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @Override
    public boolean checkUserByIdAndPassword(Long id, String password) {
        return userRepository.existsUserByIdAndPassword(id, password);
    }
    
    @Override
    public List< User > findListUser() {
        return userRepository.findAll();
    }
}
