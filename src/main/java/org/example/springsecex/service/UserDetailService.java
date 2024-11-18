package org.example.springsecex.service;

import org.example.springsecex.model.User;
import org.example.springsecex.model.UserPrincipal;
import org.example.springsecex.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    private User user;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username+" not found");
        }
        return new UserPrincipal(user);
    }
}
