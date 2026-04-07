package com.h7.synapseai.app.controller;

import com.h7.synapseai.app.dto.UserDto;
import com.h7.synapseai.app.model.User;
import com.h7.synapseai.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal OAuth2User oauth2User) {
        if (oauth2User == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }

        String googleId = oauth2User.getAttribute("sub");
        Optional<User> userOptional = userRepository.findByGoogleId(googleId);

        return userOptional
                .map(user -> ResponseEntity.ok(new UserDto(user.getName(), user.getImageUrl())))
                .orElseGet(() -> ResponseEntity.status(404).build()); // Not Found
    }
}
