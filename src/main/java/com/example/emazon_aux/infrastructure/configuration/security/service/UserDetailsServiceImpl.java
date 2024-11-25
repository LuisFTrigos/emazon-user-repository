package com.example.emazon_aux.infrastructure.configuration.security.service;

import com.example.emazon_aux.infrastructure.output.entity.UserEntity;
import com.example.emazon_aux.infrastructure.output.respository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository
                .findOneByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("The user with email " + email + " doesn't exist."));

        return new UserDetailsImpl(userEntity);
    }

}
