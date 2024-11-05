package com.kokwai.api.users.controller;

import com.kokwai.api.users.model.CreateUserRequestModel;
import com.kokwai.api.users.model.CreateUserResponseModel;
import com.kokwai.api.users.service.UserService;
import com.kokwai.api.users.shared.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final Environment environment;
    private final UserService userService;

    @GetMapping("/status/check")
    public String status() {
        return "users-ws working on port " + environment.getProperty("local.server.port");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = userService.createUser(userDto);
        CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(returnValue);
    }

}
