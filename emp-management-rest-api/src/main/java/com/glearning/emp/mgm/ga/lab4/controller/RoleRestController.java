package com.glearning.emp.mgm.ga.lab4.controller;

import com.glearning.emp.mgm.ga.lab4.dto.UserRole;
import com.glearning.emp.mgm.ga.lab4.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a rest controller class for User's role management.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@RestController
@RequestMapping("/api/role")
@Slf4j
public class RoleRestController {
    private final RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * This method is responsible to accept request to save user's role.
     *
     * @param role User's role like USER or ADMIN
     * @return Object of {@link ResponseEntity}
     */
    @PostMapping("/save")
    public ResponseEntity<UserRole> saveUserRole(@RequestBody UserRole role) {
        try {
            UserRole userRole = roleService.saveUserRole(role);
            return new ResponseEntity<>(userRole, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Save User Error := ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
