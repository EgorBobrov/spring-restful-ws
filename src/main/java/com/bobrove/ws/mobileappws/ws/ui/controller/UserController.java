package com.bobrove.ws.mobileappws.ws.ui.controller;

import com.bobrove.ws.mobileappws.ws.ui.model.request.UserDetailsRequestModel;
import com.bobrove.ws.mobileappws.ws.ui.model.response.UserDetailsResponseModel;
import com.bobrove.ws.mobileappws.ws.service.UserService;
import com.bobrove.ws.mobileappws.ws.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/{userId}")
    public UserDetailsResponseModel getUser(@PathVariable("userId") String userId) {
        UserDetailsResponseModel responseModel = new UserDetailsResponseModel();

        UserDto userDto = userService.getUserByUserId(userId);
        BeanUtils.copyProperties(userDto, responseModel);
        return responseModel;
    }

    @PostMapping
    public UserDetailsResponseModel createUser(@Valid @RequestBody
                                                           UserDetailsRequestModel userDetails) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);

        UserDetailsResponseModel responseModel = new UserDetailsResponseModel();
        BeanUtils.copyProperties(createdUser, responseModel);
        return responseModel;
    }

    @PutMapping
    public String updateUser() {
        return "updateUser() called";
    }

    @DeleteMapping
    public String deleteUser() {
        return "deleteUser() called";
    }
}
