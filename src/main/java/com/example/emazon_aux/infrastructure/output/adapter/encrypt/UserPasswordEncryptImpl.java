package com.example.emazon_aux.infrastructure.output.adapter.encrypt;

import com.example.emazon_aux.domain.util.password.UserPasswordEncrypt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserPasswordEncryptImpl implements UserPasswordEncrypt {

    private final PasswordEncoder encryptor;

    @Override
    public String passwordEncoder(String password) {
        return encryptor.encode(password);
    }
}
