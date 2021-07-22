package com.bobrove.ws.mobileappws.ws.web.controller;

import com.bobrove.ws.mobileappws.ws.web.model.request.BaseUserRequestDto;
import com.bobrove.ws.mobileappws.ws.web.model.request.UpdateUserDetailsRequestDto;
import com.bobrove.ws.mobileappws.ws.web.model.request.UserDetailsRequestDto;
import com.bobrove.ws.mobileappws.ws.web.model.response.OperationStatusDto;
import com.bobrove.ws.mobileappws.ws.web.model.response.UserResponseDto;
import com.bobrove.ws.mobileappws.ws.service.UserService;
import com.bobrove.ws.mobileappws.ws.service.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(path = "/{userId}")
    public UserResponseDto getUser(@PathVariable("userId") String userId) {
        return convertToDto(userService.getUserByUserId(userId));
    }

    @PostMapping
    public UserResponseDto createUser(@RequestBody @Valid UserDetailsRequestDto userDetails) {
        return convertToDto(userService.createUser(convertToModel(userDetails)));
    }

    // TODO: handle updating the email and the password differently
    @PutMapping(path = "/{userId}")
    public UserResponseDto updateUser(@PathVariable("userId") String userId,
                                      @RequestBody @Valid UpdateUserDetailsRequestDto userDetails) {
        return convertToDto(userService.updateUserByUserId(userId, convertToModel(userDetails)));
    }

    @DeleteMapping(path = "/{userId}")
    public OperationStatusDto deleteUser(@PathVariable("userId") String userId) {
        return userService.deleteUser(userId);
    }

    private UserResponseDto convertToDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    private User convertToModel(BaseUserRequestDto userRequestDto) {
        return modelMapper.map(userRequestDto, User.class);
    }
}
