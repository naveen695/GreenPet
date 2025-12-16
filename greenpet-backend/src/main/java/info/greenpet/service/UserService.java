package info.greenpet.service;

import info.greenpet.model.User;
import info.greenpet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User register(User user, String rawPassword) {
        user.setPasswordHash(passwordEncoder.encode(rawPassword));
        if (user.getRoles() == null) {
            user.setRoles(Collections.singletonList("ROLE_USER"));
        }
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkPassword(String rawPassword, String hash) {
        return passwordEncoder.matches(rawPassword, hash);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if (u.getRoles() != null) {
            u.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));
        }
        return new org.springframework.security.core.userdetails.User(u.getEmail(), u.getPasswordHash(), authorities);
    }
}
