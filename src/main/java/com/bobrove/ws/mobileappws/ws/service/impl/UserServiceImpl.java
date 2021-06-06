package com.bobrove.ws.mobileappws.ws.service.impl;

import com.bobrove.ws.mobileappws.ws.data.entity.UserEntity;
import com.bobrove.ws.mobileappws.ws.data.repository.UserRepository;
import com.bobrove.ws.mobileappws.ws.service.UserService;
import com.bobrove.ws.mobileappws.ws.shared.Utils;
import com.bobrove.ws.mobileappws.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity existingUserWithSameEmail = userRepository.findByEmail(userDto.getEmail());
        if (existingUserWithSameEmail != null) {
            throw new RuntimeException(String.format("User with email %s already exists.", userDto.getEmail()));
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setEncryptedPassword("test");
        userEntity.setUserId(Utils.generateUserId());

        UserEntity savedEntity = userRepository.save(userEntity);

        UserDto result = new UserDto();
        BeanUtils.copyProperties(savedEntity, result);
        return result;
    }
}
