package org.Arquitech.Gymrat.Authentication.service;

import lombok.RequiredArgsConstructor;
import org.Arquitech.Gymrat.Authentication.domain.model.entity.Role;
import org.Arquitech.Gymrat.Authentication.domain.model.entity.User;
import org.Arquitech.Gymrat.Authentication.domain.persistence.UserRepository;
import org.Arquitech.Gymrat.Authentication.jwt.JwtService;
import org.Arquitech.Gymrat.Authentication.resource.AuthResponse;
import org.Arquitech.Gymrat.Authentication.resource.ChangePasswordRequest;
import org.Arquitech.Gymrat.Authentication.resource.LoginRequest;
import org.Arquitech.Gymrat.Authentication.resource.RegisterRequest;
import org.Arquitech.Gymrat.Shared.exception.CustomException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            UserDetails user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

            String token = jwtService.getToken(user);
            return AuthResponse.builder().token(token).build();
        } catch (Exception ex) {
            throw new CustomException("Authentication failed: " + ex.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    public AuthResponse changePassword(ChangePasswordRequest request) {

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new CustomException("New passwords do not match", HttpStatus.BAD_REQUEST);
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        UserDetails user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new CustomException("User not found", HttpStatus.NOT_FOUND));

        userRepository.findById(((User) user).getId())
                .ifPresent(userToUpdate -> {
                    userToUpdate.setPassword(passwordEncoder.encode(request.getNewPassword()));
                    userRepository.save(userToUpdate);
                });

        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }


    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .gymName(request.getGymName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .city(request.getCity())
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}
