package com.ytudak.malzeme.services;

import com.ytudak.malzeme.model.User;
import com.ytudak.malzeme.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServices implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByUserName(s);
        user.orElseThrow(()->new UsernameNotFoundException("user name not found "));

        return user.map(UserDetail::new).get();
    }
}
