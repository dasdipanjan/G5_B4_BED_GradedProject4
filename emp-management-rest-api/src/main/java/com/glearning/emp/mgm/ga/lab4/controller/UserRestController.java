package com.glearning.emp.mgm.ga.lab4.controller;

import com.glearning.emp.mgm.ga.lab4.dto.UserInfo;
import com.glearning.emp.mgm.ga.lab4.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is a rest controller class for User management.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method is responsible to accept request to save user information in database.
     *
     * @param userInfo Object of {@link UserInfo}
     * @return Object of {@link ResponseEntity}
     */
    @PostMapping("/save")
    public ResponseEntity<UserInfo> saveUserInformation(@RequestBody UserInfo userInfo) {
        try {
            UserInfo savedUserInfo = userService.saveUserInformation(userInfo);
            return new ResponseEntity<>(savedUserInfo, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error Message : ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
