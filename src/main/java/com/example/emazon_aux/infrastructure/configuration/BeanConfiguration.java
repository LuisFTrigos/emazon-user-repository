package com.example.emazon_aux.infrastructure.configuration;

import com.example.emazon_aux.domain.api.IUserServicePort;
import com.example.emazon_aux.domain.spi.IUserPersistencePort;
import com.example.emazon_aux.domain.usecase.UserUseCase;
import com.example.emazon_aux.domain.util.password.UserPasswordEncrypt;
import com.example.emazon_aux.infrastructure.output.adapter.UserJpaAdapter;
import com.example.emazon_aux.infrastructure.output.adapter.encrypt.UserPasswordEncryptImpl;
import com.example.emazon_aux.infrastructure.output.mapper.IRoleEntityMapper;
import com.example.emazon_aux.infrastructure.output.mapper.IUserEntityMapper;
import com.example.emazon_aux.infrastructure.output.respository.IRoleRepository;
import com.example.emazon_aux.infrastructure.output.respository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), userPasswordEncrypt());
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, roleRepository, userEntityMapper, roleEntityMapper);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserPasswordEncrypt userPasswordEncrypt(){
        return new UserPasswordEncryptImpl(encoder());
    }

}
