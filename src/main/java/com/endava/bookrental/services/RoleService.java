package com.endava.bookrental.services;

import com.endava.bookrental.models.Role;
import com.endava.bookrental.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles(){
       return roleRepository.findAll();
    }

    public List<Role> getRolesForRoleId(Integer roleId){
        return roleRepository.findRoleById(roleId);
    }
}
