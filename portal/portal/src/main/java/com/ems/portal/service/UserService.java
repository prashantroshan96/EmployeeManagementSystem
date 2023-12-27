package com.ems.portal.service;

import com.ems.portal.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUser(long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(long id, UserDto userDto);
    void deleteUser(long id);
}
