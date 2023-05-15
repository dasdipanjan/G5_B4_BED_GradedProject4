package com.glearning.emp.mgm.ga.lab4.services.impl;

import com.glearning.emp.mgm.ga.lab4.dto.UserRole;
import com.glearning.emp.mgm.ga.lab4.entity.Role;
import com.glearning.emp.mgm.ga.lab4.entity.User;
import com.glearning.emp.mgm.ga.lab4.repository.RoleRepository;
import com.glearning.emp.mgm.ga.lab4.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This is an implementation class of {@link RoleService}. This class is responsible to save user's role information in database.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * This method is responsible to save User's role information in database.
     *
     * @param role Object of {@link UserRole}
     * @return Object of {@link UserRole}
     */
    @Override
    public UserRole saveUserRole(UserRole role) {
        UserRole userRole = new UserRole();
        Role requestedRole = new Role();
        BeanUtils.copyProperties(role, requestedRole);
        Role savedRole = roleRepository.save(requestedRole);
        BeanUtils.copyProperties(savedRole, userRole);
        return userRole;
    }
}
