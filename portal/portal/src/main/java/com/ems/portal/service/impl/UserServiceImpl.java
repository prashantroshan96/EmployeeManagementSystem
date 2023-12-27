package com.ems.portal.service.impl;

import com.ems.portal.dto.UserDto;
import com.ems.portal.entity.User;
import com.ems.portal.exception.ResourceNotFoundException;
import com.ems.portal.mapper.UserMapper;
import com.ems.portal.repository.UserRepository;
import com.ems.portal.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(long id, UserDto userDto){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found with the given id : " + id)
        ) ;

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setAdmin(userDto.getAdmin());

        User updatedUser = userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUser);
    }

    public void deleteUser(long id){
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Employee not found with the given id : " + id);
        }
    }
}
