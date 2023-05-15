package com.glearning.emp.mgm.ga.lab4.services.impl;

import com.glearning.emp.mgm.ga.lab4.dto.UserInfo;
import com.glearning.emp.mgm.ga.lab4.dto.UserRole;
import com.glearning.emp.mgm.ga.lab4.entity.Role;
import com.glearning.emp.mgm.ga.lab4.entity.User;
import com.glearning.emp.mgm.ga.lab4.repository.UserRepository;
import com.glearning.emp.mgm.ga.lab4.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is an implementation class of {@link UserService} interface.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * This method is responsible to save user information in databse.
     *
     * @param userInfo Object of {@link UserInfo}.
     * @return Object of {@link UserInfo}
     */
    @Override
    public UserInfo saveUserInformation(UserInfo userInfo) {
        User user = new User();
        user.setUsername(userInfo.getUsername());
        user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        user.setRoles(this.copyUserRoleToRole(userInfo.getRoles()));
        User savedUser = userRepository.saveAndFlush(user);
        UserInfo savedUserInfo = new UserInfo();
        savedUserInfo.setUsername(savedUser.getUsername());
        savedUserInfo.setPassword(savedUser.getPassword());
        savedUserInfo.setRoles(this.copyRoleToUserRole(savedUser.getRoles()));
        return savedUserInfo;
    }

    /**
     * This method is responsible to copy data from {@link UserRole} class to {@link Role} class.
     *
     * @param roles List of object of {@link UserRole}
     * @return List of Object of {@link Role}
     */
    private List<Role> copyUserRoleToRole(List<UserRole> roles) {
        List<Role> roleList = new ArrayList<>();
        roles.forEach(userRole -> {
            Role role = new Role();
            role.setId(userRole.getId());
            role.setName(userRole.getName());
            roleList.add(role);
        });
        return roleList;
    }

    /**
     * This method is responsible to copy data of Role class to {@link UserRole} class.
     *
     * @param roles List of Object of Role class.
     * @return List of Object of {@link UserRole} class.
     */
    private List<UserRole> copyRoleToUserRole(List<Role> roles) {
        List<UserRole> roleList = new ArrayList<>();
        roles.forEach(userRole -> {
            UserRole role = new UserRole();
            role.setId(userRole.getId());
            role.setName(userRole.getName());
            roleList.add(role);
        });
        return roleList;
    }
}
