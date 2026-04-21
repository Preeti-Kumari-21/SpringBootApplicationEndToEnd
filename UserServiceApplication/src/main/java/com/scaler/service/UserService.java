package com.scaler.service;

import com.scaler.dto.UserRequestDTO;
import com.scaler.dto.UserResponseDTO;
import com.scaler.exception.UserNotFoundException;
import com.scaler.repository.UserRepository;
import com.scaler.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponseDTO getUserById(int id) {
        User user = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id "+id));

        return UserResponseDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        User savedUser = userRepository.save(user);
        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .build();
    }

    public UserResponseDTO updateUser(int id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id "+id));

        existingUser.setName(userRequestDTO.getName());
        User savedUser = userRepository.save(existingUser);

        return UserResponseDTO.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .build();
    }

    public void deleteUserById(int id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException("User not found with id "+id);
        }
        userRepository.deleteById(id);
    }
}
