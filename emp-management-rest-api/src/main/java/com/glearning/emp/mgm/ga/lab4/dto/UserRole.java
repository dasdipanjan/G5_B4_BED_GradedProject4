package com.glearning.emp.mgm.ga.lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This is a dto class which is responsible to carry user' role related information.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private Integer id;
    private String name;
}
