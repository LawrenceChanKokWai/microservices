package com.kokwai.api.users.shared;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter @Setter
public class UserDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 2089760521022519911L;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userId;
    private String encryptedPassword;

}
