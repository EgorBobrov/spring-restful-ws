package com.bobrove.ws.mobileappws.ws.service.impl;

import com.bobrove.ws.mobileappws.ws.data.entity.UserEntity;
import com.bobrove.ws.mobileappws.ws.data.repository.UserRepository;
import com.bobrove.ws.mobileappws.ws.service.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.bobrove.ws.mobileappws.ws.TestUtils.createUserEntity;
import static com.bobrove.ws.mobileappws.ws.TestUtils.createUserModel;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Spy
    private ModelMapper modelMapper;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void testGetUserByEmail() {
        String email = "test@email.com";
        UserEntity entity = createUserEntity(email);
        when(userRepository.findByEmail(eq(email))).thenReturn(entity);
        when(modelMapper.map(entity, User.class)).thenCallRealMethod();

        User userByEmail = userService.getUserByEmail(email);
        assertEquals(userByEmail, createUserModel(entity));
    }

}