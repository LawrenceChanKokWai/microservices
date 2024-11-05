package com.kokwai.api.users.data;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
@Table(name = "users")
@Entity
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 8827544645601842531L;

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String encryptedPassword;

}
