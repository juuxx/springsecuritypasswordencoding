package com.example.springsecuritypasswordencoding.security.service;

import com.example.springsecuritypasswordencoding.entity.Customer;
import com.example.springsecuritypasswordencoding.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("customUserService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerService userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Customer userEntity = userRepository.findUserByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        UserDetails user = User.withUsername(userEntity.getEmail()).password(userEntity.getPassword()).authorities("USER").build();
        return user;
    }
}
