package com.ytudak.malzeme;

import com.ytudak.malzeme.entity.User;
import com.ytudak.malzeme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsUserByUserName("admin")) {
            User admin = new User();
            admin.setUserName("admin");
            admin.setPassword(new BCryptPasswordEncoder().encode("1"));
            admin.setActive(true);
            admin.setRole("ROLE_ADMIN");
            userRepository.save(admin);
        }
        if (!userRepository.existsUserByUserName("ytudak")) {
            User user = new User();
            user.setUserName("ytudak");
            user.setPassword(new BCryptPasswordEncoder().encode("1982"));
            user.setActive(true);
            user.setRole("ROLE_USER");
            userRepository.save(user);
        }
        if (!userRepository.existsUserByUserName("baskan")) {
            User baskan = new User();
            baskan.setUserName("baskan");
            baskan.setPassword(new BCryptPasswordEncoder().encode("1"));
            baskan.setActive(true);
            baskan.setRole("ROLE_BASKAN");
            userRepository.save(baskan);
        }
    }
}
