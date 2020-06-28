package com.inmost.tasktracker.controller;

import com.inmost.tasktracker.model.User;
import com.inmost.tasktracker.model.dto.UserDto;
import com.inmost.tasktracker.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{user-id}")
    public UserDto get(@PathVariable("user-id") long userId) {
        return mapUserToUserDto(userService.get(userId));
    }

    @PutMapping
    public UserDto update(@RequestBody @Valid UserDto userDto) {
        return mapUserToUserDto(userService.update(mapUserDtoToUser(userDto)));
    }

    @GetMapping
    public List<UserDto> getAll(@RequestParam(name = "page") @Min(0) int pageNumber) {
        return userService.getAll(pageNumber).stream()
                .map(this::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") long userId) {
        userService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private UserDto mapUserToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User mapUserDtoToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
