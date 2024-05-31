package com.avisto.webimages.services;

import com.avisto.webimages.model.Image;
import com.avisto.webimages.model.User;
import com.avisto.webimages.model.enums.Role;
import com.avisto.webimages.repositories.ImageRepository;
import com.avisto.webimages.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with name: {}", user.getUsername());
        userRepository.save(user);
        return true;
    }

    public List<User> userList() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveImage(User user, MultipartFile imageFile) throws IOException {
        Image image;
        if (imageFile.getSize() != 0) {
            image = toImageEntity(imageFile);
            user.addImageToUser(image);
            userRepository.save(user);
        }
    }

    public void deleteImage(Long imageId) {
        imageRepository.deleteById(imageId);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    private Image toImageEntity(MultipartFile imageFile) throws IOException {
        Image image = new Image();
        image.setName(imageFile.getName());
        image.setOriginalFileName(imageFile.getOriginalFilename());
        image.setContentType(imageFile.getContentType());
        image.setSize(imageFile.getSize());
        image.setBytes(imageFile.getBytes());

        return image;
    }
}
