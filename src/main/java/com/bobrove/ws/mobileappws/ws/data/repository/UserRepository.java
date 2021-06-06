package com.bobrove.ws.mobileappws.ws.data.repository;

import com.bobrove.ws.mobileappws.ws.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
