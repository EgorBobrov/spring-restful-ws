package com.bobrove.ws.mobileappws.ws.service.impl;

import com.bobrove.ws.mobileappws.ws.data.entity.UserEntity;
import com.bobrove.ws.mobileappws.ws.data.repository.UserRepository;
import com.bobrove.ws.mobileappws.ws.service.UserService;
import com.bobrove.ws.mobileappws.ws.shared.Utils;
import com.bobrove.ws.mobileappws.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity existingUserWithSameEmail = userRepository.findByEmail(userDto.getEmail());
        if (existingUserWithSameEmail != null) {
            throw new RuntimeException(String.format("User with email %s already exists.", userDto.getEmail()));
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setUserId(Utils.generateUserId());

        UserEntity savedEntity = userRepository.save(userEntity);

        UserDto result = new UserDto();
        BeanUtils.copyProperties(savedEntity, result);
        return result;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        UserDto result = new UserDto();
        BeanUtils.copyProperties(userEntity, result);
        return result;
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException(userId);
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto);
        return userDto;
    }

    @Override
    public UserDto updateUserByUserId(String userId, UserDto dataToUpdateUser) {
        UserEntity currentUserData = userRepository.findByUserId(userId);
        if (currentUserData == null) {
            throw new UsernameNotFoundException(userId);
        }
        currentUserData.setFirstName(dataToUpdateUser.getFirstName());
        currentUserData.setLastName(dataToUpdateUser.getLastName());
        UserEntity updatedUser = userRepository.save(currentUserData);

        UserDto result = new UserDto();
        BeanUtils.copyProperties(updatedUser, result);
        return result;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), List.of());
    }
}
