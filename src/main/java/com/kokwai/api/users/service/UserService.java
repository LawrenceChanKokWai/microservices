package com.kokwai.api.users.service;


import com.kokwai.api.users.shared.UserDto;

public interface UserService {

    UserDto createUser(UserDto userDetails);

}
