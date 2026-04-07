package com.h7.synapseai.app.service;

import com.h7.synapseai.app.model.User;
import com.h7.synapseai.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("--- DEBUG: CustomOAuth2UserService.loadUser() method STARTED. ---");

        OAuth2User oauth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oauth2User.getAttributes();

        System.out.println("--- DEBUG: Attributes received from Google: " + attributes);

        String email = (String) attributes.get("email");
        if (email == null) {
            System.err.println("--- CRITICAL ERROR: Email is NULL from Google attributes. ---");
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }
        System.out.println("--- DEBUG: User email extracted: " + email);

        Optional<User> userOptional = userRepository.findByEmail(email);

        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            System.out.println("--- DEBUG: User found in database. Updating user: " + user.getEmail());
            user.setName((String) attributes.get("name"));
            user.setImageUrl((String) attributes.get("picture"));
        } else {
            System.out.println("--- DEBUG: User not found. Creating a new user...");
            user = new User();
            user.setEmail(email);
            user.setName((String) attributes.get("name"));
            user.setImageUrl((String) attributes.get("picture"));
        }

        try {
            userRepository.save(user);
            System.out.println("--- SUCCESS: User successfully saved or updated in the database! ---");
        } catch (Exception e) {
            System.err.println("--- CRITICAL ERROR: FAILED TO SAVE USER TO DATABASE! ---");
            e.printStackTrace();
        }

        return oauth2User;
    }
}
