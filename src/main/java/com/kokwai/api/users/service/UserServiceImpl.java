package com.kokwai.api.users.service;

import com.kokwai.api.users.data.UserEntity;
import com.kokwai.api.users.repository.UserRepository;
import com.kokwai.api.users.shared.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDetails) {

        userDetails.setUserId(UUID.randomUUID().toString());

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);

        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);
        userEntity.setEncryptedPassword("test");

        userRepository.save(userEntity);

        return modelMapper.map(userEntity, UserDto.class);
    }
}
