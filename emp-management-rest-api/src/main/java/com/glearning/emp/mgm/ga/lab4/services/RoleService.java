package com.glearning.emp.mgm.ga.lab4.services;

import com.glearning.emp.mgm.ga.lab4.dto.UserRole;

/**
 * This is an interface which is defining the contract of user's role management system.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
public interface RoleService {
    UserRole saveUserRole(UserRole role);
}
