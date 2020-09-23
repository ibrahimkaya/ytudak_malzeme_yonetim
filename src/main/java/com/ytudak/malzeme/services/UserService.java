package com.ytudak.malzeme.services;

import com.ytudak.malzeme.entity.User;
import com.ytudak.malzeme.model.UserDTO;
import com.ytudak.malzeme.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
  User: Ufuk
  Date: 23.09.2020 10:53
*/
@Service
public class UserService {

    final private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean changePassword(UserDTO userDTO) {
        Optional<User> currentUser = userRepository.findByUserName(profilePage());
        if (currentUser.isPresent()) {
            String newPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
            currentUser.get().setPassword(newPassword);
            userRepository.save(currentUser.get());
            return true;

        }
        return false;
    }

    // get current users username
    public String profilePage() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
