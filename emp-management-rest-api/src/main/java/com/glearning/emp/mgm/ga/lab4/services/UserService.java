package com.glearning.emp.mgm.ga.lab4.services;

import com.glearning.emp.mgm.ga.lab4.dto.UserInfo;

/**
 * This is an interface which is defining the contract of user management system.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
public interface UserService {
    UserInfo saveUserInformation(UserInfo userInfo);
}
