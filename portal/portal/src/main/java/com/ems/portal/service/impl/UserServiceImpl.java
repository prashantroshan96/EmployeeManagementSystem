package com.ems.portal.service.impl;

import com.ems.portal.dto.UserDto;
import com.ems.portal.entity.User;
import com.ems.portal.exception.ResourceNotFoundException;
import com.ems.portal.mapper.UserMapper;
import com.ems.portal.repository.UserRepository;
import com.ems.portal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(savedUser);
    }

    public UserDto getUser(long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found With the given Id : " + id));
        UserDto userDto = UserMapper.mapToUserDto(user);
        return userDto;
    }
}
