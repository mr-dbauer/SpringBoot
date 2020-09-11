package com.springBootBauerProject.SpringBoot.service;

import com.springBootBauerProject.SpringBoot.model.Role;
import com.springBootBauerProject.SpringBoot.model.User;
import com.springBootBauerProject.SpringBoot.repository.RoleRepository;
import com.springBootBauerProject.SpringBoot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService, RoleService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Transactional
    @Override
    public <S extends User> S save(S user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }


    @Transactional(readOnly = true)
    @Override
    public Iterable findAll() {
        return userRepository.findAll();
    }


    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    @Transactional
    @Override
    public Iterable<Role> findAllRoles() {
        return roleRepository.findAll();
    }
}
