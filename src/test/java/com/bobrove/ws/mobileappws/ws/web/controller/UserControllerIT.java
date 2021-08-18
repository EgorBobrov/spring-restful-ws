package com.bobrove.ws.mobileappws.ws.web.controller;

import com.bobrove.ws.mobileappws.ws.service.UserService;
import com.bobrove.ws.mobileappws.ws.service.model.User;
import com.bobrove.ws.mobileappws.ws.web.model.request.UpdateUserDetailsRequestDto;
import com.bobrove.ws.mobileappws.ws.web.model.request.UserDetailsRequestDto;
import com.bobrove.ws.mobileappws.ws.web.model.response.OperationStatusDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static com.bobrove.ws.mobileappws.ws.TestUtils.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerIT {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ModelMapper modelMapper;

    @MockBean
    private UserService userService;

    private static final String URL = "/users/";

    private static final String USER_ID = "TEST_USER_ID";
    private static final String USER_FIRST_NAME = "John";
    private static final String USER_LAST_NAME = "Doe";
    private static final String USER_PASSWORD = "password";
    private static final String USER_EMAIL = "test@app.com";

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser
    void shouldGetUser() throws Exception {
        when(userService.getUserByUserId(USER_ID))
                .thenReturn(createUserModel(createUserEntity(USER_EMAIL, USER_ID)));

        mockMvc.perform(get(URL + USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId")
                        .value(USER_ID));
    }

    @Test
    @WithMockUser
    void shouldGetUsers() throws Exception {
        when(userService.getUsers(0, 25))
                .thenReturn(List.of(createUserModel(
                        createUserEntity(USER_EMAIL, USER_ID))));

        mockMvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].userId")
                        .value(USER_ID));
    }

    @Test
    @WithMockUser
    void shouldCreateUser() throws Exception {
        UserDetailsRequestDto creationRequest =
                createUserDetailsRequestDto(USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_PASSWORD);
        User user = modelMapper.map(creationRequest, User.class);
        when(userService.createUser(user)).thenReturn(createUserModel(creationRequest));

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.format("{\"firstName\": \"%s\", " +
                                    "\"lastName\": \"%s\", " +
                                    "\"email\":\"%s\"," +
                                    "\"password\": \"%s\"}",
                                USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_PASSWORD)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(USER_ID));
    }

    @Test
    @WithMockUser
    void shouldUpdateUser() throws Exception {
        UpdateUserDetailsRequestDto updateRequest =
                createUserDetailsRequestDto(USER_FIRST_NAME, USER_LAST_NAME);
        User user = modelMapper.map(updateRequest, User.class);
        when(userService.updateUserByUserId(USER_ID, user)).thenReturn(createUserModel(updateRequest, USER_EMAIL));

        mockMvc.perform(put(URL + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.format("{\"firstName\": \"%s\", " +
                                "\"lastName\": \"%s\"}",
                        USER_FIRST_NAME, USER_LAST_NAME)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(USER_ID));
    }

    @Test
    @WithMockUser
    void shouldDeleteUser() throws Exception {
        when(userService.deleteUser(USER_ID)).thenReturn(
                new OperationStatusDto(OperationStatusDto.OperationStatus.SUCCESS,
                                       OperationStatusDto.OperationName.DELETE));

        mockMvc.perform(delete(URL + USER_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operationStatus").value(OperationStatusDto.
                        OperationStatus.SUCCESS.toString()))
                .andExpect(jsonPath("$.operationName").value(OperationStatusDto.
                        OperationName.DELETE.toString()));
    }
}