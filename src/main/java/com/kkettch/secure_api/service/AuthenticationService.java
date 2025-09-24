package com.kkettch.secure_api.service;

import com.kkettch.secure_api.entity.Profile;
import com.kkettch.secure_api.entity.User;
import com.kkettch.secure_api.repository.ProfileRepository;
import com.kkettch.secure_api.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public void register(String username, String password, String nickname, String email) {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder().username(username).password(passwordEncoder.encode(password)).build();
        userRepository.save(user);

        Profile profile = Profile.builder().nickname(nickname).email(email).user(user).build();
        profileRepository.save(profile);
    }

    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Username doesn't exist"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtService.generateToken(user.getUsername());
    }
}
