package com.springBootBauerProject.SpringBoot.service;

import com.springBootBauerProject.SpringBoot.model.User;

public interface UserService {

    User findByLogin(String login);

    Iterable<User> findAll();

    public <S extends User> S save(S s);

    User findById(Long id);

    void deleteById(Long id);
}
