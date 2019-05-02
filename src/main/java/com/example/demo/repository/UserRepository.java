package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Linh
 * @project demo
 */
@Repository
public interface UserRepository extends JpaRepository< User, Long > {
    
    boolean existsUserByEmail(String email);
    
    boolean existsUserByUsername(String username);
    
    boolean existsUserById(Long id);
    
    boolean existsUserByIdAndPassword(Long id, String password);
}
