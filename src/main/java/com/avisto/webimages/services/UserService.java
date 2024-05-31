package com.avisto.webimages.services;

import com.avisto.webimages.model.User;
import com.avisto.webimages.model.enums.Role;
import com.avisto.webimages.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public boolean createUser(User user){
        if(userRepository.findByUsername(user.getUsername()) != null){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with name: {}", user.getUsername());
        userRepository.save(user);
        return true;
    }
    public List<User> userList(){
        return userRepository.findAll();
    }
}
