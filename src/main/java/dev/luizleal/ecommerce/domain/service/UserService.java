package dev.luizleal.ecommerce.domain.service;

import dev.luizleal.ecommerce.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private JwtService jwtService;

}
