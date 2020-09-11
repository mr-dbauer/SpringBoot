package com.springBootBauerProject.SpringBoot.service;

import com.springBootBauerProject.SpringBoot.model.Role;

import java.util.Set;

public interface RoleService {

    Role findRoleByName(String name);

    Iterable<Role> findAllRoles();

    public Set<Role> getRoles(String[] ids);
}
