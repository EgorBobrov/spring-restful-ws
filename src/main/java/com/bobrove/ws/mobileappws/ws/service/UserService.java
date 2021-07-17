package com.bobrove.ws.mobileappws.ws.service;

import com.bobrove.ws.mobileappws.ws.shared.dto.UserDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByEmail(String email);
    UserDto getUserByUserId(String userId);
    UserDto updateUserByUserId(String userId, UserDto dataToUpdateUser);
}
