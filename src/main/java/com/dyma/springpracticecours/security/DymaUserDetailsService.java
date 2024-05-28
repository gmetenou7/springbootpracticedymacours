package com.dyma.springpracticecours.security;

import com.dyma.springpracticecours.errors.specific.ResourceNotFoundException;
import com.dyma.springpracticecours.users.entity.RoleEntity;
import com.dyma.springpracticecours.users.entity.UserEntity;
import com.dyma.springpracticecours.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DymaUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;
    public DymaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws ResourceNotFoundException {

        return userRepository.findOneWithRolesByLoginIgnoreCase(login)
                .map(this::createSecurityUser)
                .orElseThrow(()->new ResourceNotFoundException("User with login " + login + " could not be found."));
    }

    private User createSecurityUser(UserEntity userEntity) {
        List<SimpleGrantedAuthority> grantedRoles = userEntity
                .getRoles()
                .stream()
                .map(RoleEntity::getName)
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new User(userEntity.getLogin(), userEntity.getPassword(), grantedRoles);
    }

}
