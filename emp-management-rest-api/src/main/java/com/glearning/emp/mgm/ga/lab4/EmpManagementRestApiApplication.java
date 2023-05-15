package com.glearning.emp.mgm.ga.lab4;

import com.glearning.emp.mgm.ga.lab4.dto.UserInfo;
import com.glearning.emp.mgm.ga.lab4.dto.UserRole;
import com.glearning.emp.mgm.ga.lab4.entity.User;
import com.glearning.emp.mgm.ga.lab4.services.EmployeeServices;
import com.glearning.emp.mgm.ga.lab4.services.RoleService;
import com.glearning.emp.mgm.ga.lab4.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible to start the employee management application.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@SpringBootApplication
@Slf4j
public class EmpManagementRestApiApplication implements CommandLineRunner {
    @Autowired
    private EmployeeServices employeeServices;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * This method is an entry point of this application. This method is called by main thread of the JVM.
     *
     * @param args Command line arguments string.
     */
    public static void main(String[] args) {
        SpringApplication.run(EmpManagementRestApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
