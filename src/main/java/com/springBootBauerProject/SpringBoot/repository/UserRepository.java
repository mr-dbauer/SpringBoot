package com.springBootBauerProject.SpringBoot.repository;

import com.springBootBauerProject.SpringBoot.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

}
