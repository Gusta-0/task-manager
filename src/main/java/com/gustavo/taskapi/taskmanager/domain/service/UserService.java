package com.gustavo.taskapi.taskmanager.domain.service;

import com.gustavo.taskapi.taskmanager.domain.entity.User;
import com.gustavo.taskapi.taskmanager.domain.repository.UserRepository;
import com.gustavo.taskapi.taskmanager.dto.CreateUserRequest;
import com.gustavo.taskapi.taskmanager.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDTO createUser(CreateUserRequest request) {
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());

        return toDTO(userRepository.save(user));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        return toDTO(findUserByIdOrThrow(id));
    }

    public UserDTO updateUser(Long id, CreateUserRequest request) {
        User user = findUserByIdOrThrow(id);

        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());

        return toDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado para exclusão com id: " + id);
        }
        userRepository.deleteById(id);
    }

    public User getByIdEntity(Long id) {
        return findUserByIdOrThrow(id);
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail());
    }

    private User findUserByIdOrThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }
}