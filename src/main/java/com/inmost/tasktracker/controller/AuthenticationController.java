package com.inmost.tasktracker.controller;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import com.auth0.jwt.JWT;
import com.inmost.tasktracker.model.User;
import com.inmost.tasktracker.model.dto.UserAuthenticationResponseDto;
import com.inmost.tasktracker.model.dto.UserLoginDto;
import com.inmost.tasktracker.model.dto.UserRegistrationDto;
import com.inmost.tasktracker.model.dto.UserResponseDto;
import com.inmost.tasktracker.security.JwtProperties;
import com.inmost.tasktracker.service.AuthenticationService;
import java.util.Date;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;

    public AuthenticationController(AuthenticationService authenticationService,
                                    AuthenticationManager authenticationManager,
                                    UserDetailsService userDetailsService,
                                    ModelMapper modelMapper) {
        this.authenticationService = authenticationService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/authentication")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    userLoginDto.getUsername(), userLoginDto.getPassword())
            );
        } catch (BadCredentialsException exception) {
            throw new RuntimeException("Incorrect username or password!", exception);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userLoginDto.getUsername());
        final String jwt = JWT.create()
                .withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));

        return ResponseEntity.ok(new UserAuthenticationResponseDto(jwt));
    }

    @PostMapping("/registration")
    public UserResponseDto register(@Valid @RequestBody
                                        UserRegistrationDto userRegistrationDto) {
        return mapUserToUserDto(
                authenticationService.add(mapUserRegistrationDtoToUser(userRegistrationDto)));
    }

    private User mapUserRegistrationDtoToUser(UserRegistrationDto userRegistrationDto) {
        return modelMapper.map(userRegistrationDto, User.class);
    }

    private UserResponseDto mapUserToUserDto(User user) {
        return modelMapper.map(user, UserResponseDto.class);
    }
}
