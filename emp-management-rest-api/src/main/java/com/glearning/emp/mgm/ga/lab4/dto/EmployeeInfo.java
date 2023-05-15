package com.glearning.emp.mgm.ga.lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a dto class which is responsible to carry information about employee.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeInfo {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
