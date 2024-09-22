package com.example.kiranastore.service;

import com.example.kiranastore.entity.User;
import com.example.kiranastore.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @PostConstruct
        public void init() {
            // Preloading some users for testing
            if (userRepository.count() == 0) {
               User admin = new User("admin", passwordEncoder.encode("password123"), "ROLE_WRITE");
               User viewer = new User("viewer", passwordEncoder.encode("password123"), "ROLE_READ");
                userRepository.save(admin);
                userRepository.save(viewer);
            }
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Optional<User> userOptional = userRepository.findByUsername(username);
            if (!userOptional.isPresent()) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }

            User user = userOptional.get();
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
        }

        private Collection<? extends GrantedAuthority> getAuthorities(User user) {
            return AuthorityUtils.createAuthorityList(user.getRole());
        }

        // Register a new user (optional)
        public User registerUser(String username, String password, String role) {
            User user = new User(username, passwordEncoder.encode(password), role);
            return userRepository.save(user);
        }
    }
