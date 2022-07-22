package com.endava.bookrental.repositories;

import com.endava.bookrental.models.EnumRoles;
import com.endava.bookrental.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    public List<Role> findRoleById(Integer roleId);
    Optional<Role> findByName(EnumRoles name);
}
