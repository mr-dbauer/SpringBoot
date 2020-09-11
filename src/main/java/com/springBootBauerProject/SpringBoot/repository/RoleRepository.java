package com.springBootBauerProject.SpringBoot.repository;

import com.springBootBauerProject.SpringBoot.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findRoleByName(String name);
}
