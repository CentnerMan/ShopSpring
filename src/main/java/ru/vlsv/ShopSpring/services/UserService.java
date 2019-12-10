package ru.vlsv.ShopSpring.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vlsv.ShopSpring.tools.SystemUser;
import ru.vlsv.ShopSpring.entities.User;

public interface UserService extends UserDetailsService {
    User findByUsername(String username);
    User save(SystemUser systemUser);
}