package com.ems.portal.service;

import com.ems.portal.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUser(long id);
}
