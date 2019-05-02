package com.example.demo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Linh
 * @project demo
 */
@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "username")})
@NoArgsConstructor
@Data
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "email", unique = true, nullable = false, length = 150)
    private String email;
    @Column(name = "username", unique = true, nullable = false, length = 30)
    private String username;
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Column(name = "status")
    private Integer status;
    
    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = 1;
        }
    }
}
