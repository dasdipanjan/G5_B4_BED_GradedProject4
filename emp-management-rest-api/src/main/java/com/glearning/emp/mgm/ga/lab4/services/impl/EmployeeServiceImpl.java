package com.glearning.emp.mgm.ga.lab4.services.impl;

import com.glearning.emp.mgm.ga.lab4.dto.EmployeeInfo;
import com.glearning.emp.mgm.ga.lab4.entity.Employee;
import com.glearning.emp.mgm.ga.lab4.entity.User;
import com.glearning.emp.mgm.ga.lab4.repository.EmployeeRepository;
import com.glearning.emp.mgm.ga.lab4.services.EmployeeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This is an implementation of {@link EmployeeServices} inteface.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeServices {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * This method is responsible to save or update user information in database.
     *
     * @param employeeInfo Object of {@link EmployeeInfo}
     * @return Object of {@link EmployeeInfo}
     */
    @Override
    public EmployeeInfo saveEmployeeInformation(EmployeeInfo employeeInfo) {
        Optional<Employee> optional = employeeRepository.findById(employeeInfo.getId());
        if (optional.isPresent()) {
            int empId = optional.get().getId();
            BeanUtils.copyProperties(employeeInfo, optional.get());
            optional.get().setId(empId);
            Employee updatedEmployee = employeeRepository.saveAndFlush(optional.get());
            BeanUtils.copyProperties(updatedEmployee, employeeInfo);
            log.info("Employee details with employee Id {} has been updated.", empId);
        } else {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeInfo, employee);
            Employee savedEmployee = employeeRepository.saveAndFlush(employee);
            BeanUtils.copyProperties(savedEmployee, employeeInfo);
            log.info("New employee record has been created with employee id := {}", savedEmployee.getId());
        }
        return employeeInfo;
    }

    /**
     * This method is responsible to retrieve list of employees from database.
     *
     * @return Object of {@link List} of {@link EmployeeInfo}
     */
    @Override
    public List<EmployeeInfo> getAllEmployees() {
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();
        List<Employee> employeeList = employeeRepository.findAll();
        if (!employeeList.isEmpty()) {
            employeeList.forEach(employee -> {
                EmployeeInfo employeeInfo = new EmployeeInfo();
                BeanUtils.copyProperties(employee, employeeInfo);
                employeeInfoList.add(employeeInfo);
            });
        }
        return employeeInfoList;
    }

    /**
     * This method is responsible to provide {@link EmployeeInfo} based on the provided employee id.
     *
     * @param empId {@link Employee} ID
     * @return EmployeeInfo wrapped in {@link Optional} object.
     */
    @Override
    public Optional<EmployeeInfo> getEmployeeById(int empId) {
        Optional<EmployeeInfo> employeeOptional;
        Optional<Employee> optional = employeeRepository.findById(empId);
        if (optional.isPresent()) {
            EmployeeInfo employeeInfo = new EmployeeInfo();
            BeanUtils.copyProperties(optional.get(), employeeInfo);
            employeeOptional = Optional.of(employeeInfo);
        } else {
            employeeOptional = Optional.empty();
        }
        return employeeOptional;
    }

    /**
     * This method is responsible to provide list of {@link EmployeeInfo} provided by first name of the employee.
     *
     * @param firstName First Name of the employee
     * @return Object of {@link List} of {@link EmployeeInfo}
     */
    @Override
    public List<EmployeeInfo> getEmployeesByFirstName(String firstName) {
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();
        List<Employee> foundEmployees = employeeRepository.findByFirstName(firstName);
        if (!foundEmployees.isEmpty()) {
            foundEmployees.forEach(employee -> {
                EmployeeInfo employeeInfo = new EmployeeInfo();
                BeanUtils.copyProperties(employee, employeeInfo);
                employeeInfoList.add(employeeInfo);
            });
        }
        return employeeInfoList;
    }

    /**
     * This method is responsible to provide list of employees sorted by their first name in ascending or descending order.
     *
     * @param direction Either Ascending or Descending Order.
     * @return Object of {@link List} type of {@link EmployeeInfo}
     */
    @Override
    public List<EmployeeInfo> getEmployeesBySortedOrder(Sort.Direction direction) {
        List<EmployeeInfo> employeeInfoList = new ArrayList<>();
        List<Employee> sortedEmpList = employeeRepository.findAll(Sort.by(direction, "firstName"));
        if (!sortedEmpList.isEmpty()) {
            sortedEmpList.forEach(employee -> {
                EmployeeInfo employeeInfo = new EmployeeInfo();
                BeanUtils.copyProperties(employee, employeeInfo);
                employeeInfoList.add(employeeInfo);
            });
        }
        return employeeInfoList;
    }

    /**
     * This method is responsible to delete employee record from database.
     *
     * @param empId Employee ID.
     * @return true if employee exists otherwise false.
     */
    @Override
    public boolean deleteByEmployeeId(int empId) {
        Optional<Employee> optional = employeeRepository.findById(empId);
        if (optional.isPresent()) {
            log.info("Employee with employee id = {} present in database", empId);
            log.info("Deleting employee details.....");
            employeeRepository.delete(optional.get());
            return true;
        }
        return false;
    }
}
