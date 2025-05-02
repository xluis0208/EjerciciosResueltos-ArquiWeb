package com.upc.authjwt20251.service;

import com.upc.authjwt20251.model.Role;
import com.upc.authjwt20251.model.User;
import com.upc.authjwt20251.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }
    public String encodePassword(String rawPassword) {
        return new BCryptPasswordEncoder().encode(rawPassword);
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public void registerNewUser(String username, String rawPassword) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodePassword(rawPassword));

        Role defaultRole = new Role();
        defaultRole.setName("USER");

        user.setRoles(List.of(defaultRole));
        userRepository.save(user);
    }
}
