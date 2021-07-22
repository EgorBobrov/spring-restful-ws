package com.bobrove.ws.mobileappws.ws.service;

import com.bobrove.ws.mobileappws.ws.service.model.User;
import com.bobrove.ws.mobileappws.ws.web.model.request.UserDetailsRequestDto;
import com.bobrove.ws.mobileappws.ws.web.model.response.OperationStatusDto;
import com.bobrove.ws.mobileappws.ws.web.model.response.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User createUser(User user);
    User getUserByEmail(String email);
    User getUserByUserId(String userId);
    User updateUserByUserId(String userId, User dataToUpdateUser);
    OperationStatusDto deleteUser(String userId);
}
