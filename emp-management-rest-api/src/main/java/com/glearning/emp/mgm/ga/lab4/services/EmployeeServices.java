package com.glearning.emp.mgm.ga.lab4.services;

import com.glearning.emp.mgm.ga.lab4.dto.EmployeeInfo;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;
/**
 * This is an interface which is defining the contract of employee management system.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
public interface EmployeeServices {
    EmployeeInfo saveEmployeeInformation(EmployeeInfo employeeInfo);

    List<EmployeeInfo> getAllEmployees();

    Optional<EmployeeInfo> getEmployeeById(int empId);

    List<EmployeeInfo> getEmployeesByFirstName(String firstName);

    List<EmployeeInfo> getEmployeesBySortedOrder(Sort.Direction direction);

    boolean deleteByEmployeeId(int empId);
}
