package com.kokwai.api.users.repository;

import com.kokwai.api.users.data.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

//    UserEntity findByEmail(String email);
//    UserEntity findByUserId(String userId);

}