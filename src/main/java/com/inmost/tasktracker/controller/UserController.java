package com.inmost.tasktracker.controller;

import com.inmost.tasktracker.model.User;
import com.inmost.tasktracker.model.dto.UserResponseDto;
import com.inmost.tasktracker.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{user-id}")
    public UserResponseDto get(@PathVariable("user-id") @Min(value = 1,
            message = "User id should not be less than 1") long userId) {
        return mapUserToUserDto(userService.getById(userId));
    }

    @PutMapping
    public UserResponseDto update(@RequestBody @Valid UserResponseDto userResponseDto) {
        return mapUserToUserDto(userService.update(mapUserDtoToUser(userResponseDto)));
    }

    @GetMapping
    public List<UserResponseDto> getAll(@RequestParam(name = "page") @Min(0) int pageNumber) {
        return userService.getAll(pageNumber).stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") @Min(value = 1,
            message = "User id should not be less than 1") long userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UserResponseDto mapUserToUserDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }

    private User mapUserDtoToUser(UserResponseDto userResponseDto) {
        return modelMapper.map(userResponseDto, User.class);
    }
}
