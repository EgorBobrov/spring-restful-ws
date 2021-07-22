package com.bobrove.ws.mobileappws.ws.service.impl;

import com.bobrove.ws.mobileappws.ws.data.entity.UserEntity;
import com.bobrove.ws.mobileappws.ws.data.repository.UserRepository;
import com.bobrove.ws.mobileappws.ws.service.UserService;
import com.bobrove.ws.mobileappws.ws.shared.Utils;
import com.bobrove.ws.mobileappws.ws.service.model.User;
import com.bobrove.ws.mobileappws.ws.web.model.request.UserDetailsRequestDto;
import com.bobrove.ws.mobileappws.ws.web.model.response.OperationStatusDto;
import com.bobrove.ws.mobileappws.ws.web.model.response.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User createUser(User user) {
        UserEntity existingUserWithSameEmail = userRepository.findByEmail(user.getEmail());
        if (existingUserWithSameEmail != null) {
            throw new RuntimeException(String.format("User with email %s already exists.", user.getEmail()));
        }

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userEntity.setUserId(Utils.generateUserId());

        UserEntity savedEntity = userRepository.save(userEntity);

        return convertToModel(savedEntity);
    }

    @Override
    public User getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return convertToModel(userEntity);
    }

    @Override
    public User getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException(userId);
        }

        return convertToModel(userEntity);
    }

    @Override
    public List<User> getUsers(int page, int limit) {
        Pageable pageRequest = PageRequest.of(page, limit);
        return userRepository.findAll(pageRequest).getContent()
                .stream().map(this::convertToModel).collect(Collectors.toList());
    }

    @Override
    public User updateUserByUserId(String userId, User dataToUpdateUser) {
        UserEntity currentUserData = userRepository.findByUserId(userId);
        if (currentUserData == null) {
            throw new UsernameNotFoundException(userId);
        }
        currentUserData.setFirstName(dataToUpdateUser.getFirstName());
        currentUserData.setLastName(dataToUpdateUser.getLastName());
        UserEntity updatedUser = userRepository.save(currentUserData);

        return convertToModel(updatedUser);
    }

    @Override
    public OperationStatusDto deleteUser(String userId) {
        UserEntity existingUserData = userRepository.findByUserId(userId);
        if (existingUserData == null) {
            throw new UsernameNotFoundException(userId);
        }
        userRepository.delete(existingUserData);
        return new OperationStatusDto(OperationStatusDto.OperationStatus.SUCCESS,
                OperationStatusDto.OperationName.DELETE);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                List.of());
    }

    private User convertToModel(UserEntity userEntity) {
        return modelMapper.map(userEntity, User.class);
    }
}
