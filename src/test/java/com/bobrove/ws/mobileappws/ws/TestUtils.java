package com.bobrove.ws.mobileappws.ws;

import com.bobrove.ws.mobileappws.ws.data.entity.UserEntity;
import com.bobrove.ws.mobileappws.ws.service.model.User;
import com.bobrove.ws.mobileappws.ws.web.model.request.UpdateUserDetailsRequestDto;
import com.bobrove.ws.mobileappws.ws.web.model.request.UserDetailsRequestDto;

public final class TestUtils {
    private TestUtils() {};

    public static UserEntity createUserEntity(String email, String userId) {
        UserEntity entity = new UserEntity();
        entity.setId(1L);
        entity.setFirstName("First");
        entity.setLastName("Last");
        entity.setUserId(userId);
        entity.setEncryptedPassword("******");
        entity.setEmailVerificationStatus(true);
        entity.setEmailVerificationToken("token");
        entity.setEmail(email);
        return entity;
    }

    public static UserEntity createUserEntity(String email) {
        return createUserEntity(email, "testUserId");
    }

    public static User createUserModel(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setUserId(entity.getUserId());
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPassword(entity.getEncryptedPassword());
        user.setEncryptedPassword(entity.getEncryptedPassword());
        user.setEmailVerificationStatus(entity.getEmailVerificationStatus());
        user.setEmailVerificationToken(entity.getEmailVerificationToken());
        return user;
    }

    public static User createUserModel(UserDetailsRequestDto requestDto) {
        User user = new User();
        user.setId(1L);
        user.setUserId("TEST_USER_ID");
        user.setEmail(requestDto.getEmail());
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setPassword("******");
        user.setEncryptedPassword("******");
        user.setEmailVerificationStatus(true);
        user.setEmailVerificationToken("token");
        return user;
    }

    public static User createUserModel(UpdateUserDetailsRequestDto requestDto, String userEmail) {
        User user = new User();
        user.setId(1L);
        user.setUserId("TEST_USER_ID");
        user.setEmail(userEmail);
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setPassword("******");
        user.setEncryptedPassword("******");
        user.setEmailVerificationStatus(true);
        user.setEmailVerificationToken("token");
        return user;
    }

    public static UserDetailsRequestDto createUserDetailsRequestDto(String firstName, String lastName,
                                                                    String email, String password) {
        UserDetailsRequestDto requestDto = new UserDetailsRequestDto();
        requestDto.setFirstName(firstName);
        requestDto.setLastName(lastName);
        requestDto.setEmail(email);
        requestDto.setPassword(password);
        return requestDto;
    }

    public static UpdateUserDetailsRequestDto createUserDetailsRequestDto(String firstName, String lastName) {
        UpdateUserDetailsRequestDto requestDto = new UpdateUserDetailsRequestDto();
        requestDto.setFirstName(firstName);
        requestDto.setLastName(lastName);
        return requestDto;
    }

}
