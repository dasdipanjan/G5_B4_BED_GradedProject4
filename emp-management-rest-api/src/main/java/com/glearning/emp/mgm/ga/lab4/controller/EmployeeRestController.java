package com.glearning.emp.mgm.ga.lab4.controller;

import com.glearning.emp.mgm.ga.lab4.dto.EmployeeId;
import com.glearning.emp.mgm.ga.lab4.dto.EmployeeInfo;
import com.glearning.emp.mgm.ga.lab4.services.EmployeeServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This is a rest controller class for employee management system. This class is responsible to accept
 * all CRUD related request from client for employee entity.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeRestController {
    @Autowired
    private EmployeeServices employeeServices;

    /**
     * This method is responsible to accept save employee request from client.
     *
     * @param employeeInfo object of {@link EmployeeInfo}
     * @return ResponseEntity object type of {@link EmployeeId}
     */
    @PostMapping("/save")
    public ResponseEntity<EmployeeId> saveEmployeeInformation(@RequestBody EmployeeInfo employeeInfo) {
        try {
            EmployeeInfo savedEmpInfo = employeeServices.saveEmployeeInformation(employeeInfo);
            return (savedEmpInfo != null && savedEmpInfo.getId() > 0) ?
                    new ResponseEntity<>(new EmployeeId(savedEmpInfo.getId()), HttpStatus.CREATED) :
                    new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            log.error("Save Employee Error := ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is responsible list of employee request from client over HTTP.
     *
     * @return ResponseEntity object which wrapps {@link List} of {@link EmployeeInfo}
     */
    @GetMapping("/list")
    public ResponseEntity<List<EmployeeInfo>> getAllEmployees() {
        return new ResponseEntity<>(employeeServices.getAllEmployees(), HttpStatus.OK);
    }

    /**
     * This method is responsible to accept retrieve specific employee request from its client.
     *
     * @param employeeId Employee ID as path variable.
     * @return Object of {@link ResponseEntity} of type {@link EmployeeInfo}
     */
    @GetMapping("/specific/{empid}")
    public ResponseEntity<EmployeeInfo> getSpecificEmployeeById(@PathVariable("empid") int employeeId) {
        Optional<EmployeeInfo> optional = employeeServices.getEmployeeById(employeeId);
        return optional.map(employeeInfo -> new ResponseEntity<>(employeeInfo, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * This method is responsible to accept update employee request from client.
     *
     * @param employeeInfo Object of {@link EmployeeInfo}
     * @return Object of {@link ResponseEntity} of type {@link EmployeeInfo}
     */
    @PostMapping("/update")
    public ResponseEntity<EmployeeInfo> updateEmployeeInformation(@RequestBody EmployeeInfo employeeInfo) {
        EmployeeInfo savedEmpInfo = employeeServices.saveEmployeeInformation(employeeInfo);
        return (savedEmpInfo != null && savedEmpInfo.getId() > 0) ? new ResponseEntity<>(savedEmpInfo, HttpStatus.CREATED) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method is responsible to accept delete employee by ID requet from client.
     *
     * @param empid Employee ID
     * @return Response message in string format.
     */
    @DeleteMapping("/delete/{empid}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("empid") Integer empid) {
        return employeeServices.deleteByEmployeeId(empid) ?
                new ResponseEntity<>("Employee has been deleted succeessfully", HttpStatus.OK) :
                new ResponseEntity<>("Employee with id " + empid + " not found in database", HttpStatus.NOT_FOUND);
    }

    /**
     * This method is responsible to accept request to retrieve employees by their first name.
     *
     * @param firstName First Name of the employee.
     * @return Object of {@link ResponseEntity}
     */
    @GetMapping("/search/{firstname}")
    public ResponseEntity<List<EmployeeInfo>> fetchEmployeesByFirstName(@PathVariable("firstname") String firstName) {
        try {
            List<EmployeeInfo> searchedEmployees = employeeServices.getEmployeesByFirstName(firstName);
            return (searchedEmployees != null && !searchedEmployees.isEmpty()) ?
                    new ResponseEntity<>(searchedEmployees, HttpStatus.OK) :
                    new ResponseEntity<>(searchedEmployees, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Search Employee Error := ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This method is responsible to accept request to retrieve all employees sorted by their first name.
     *
     * @param order Order type either desc or asc
     * @return Object of {@link ResponseEntity}
     */
    @GetMapping("/sort")
    public ResponseEntity<List<EmployeeInfo>> fetchEmployeesByFirstNameInSortedOrder(@RequestParam("order") String order) {
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (order.equals("asc")) {
            return new ResponseEntity<>(employeeServices.getEmployeesBySortedOrder(Sort.Direction.ASC), HttpStatus.OK);
        } else if (order.equals("desc")) {
            return new ResponseEntity<>(employeeServices.getEmployeesBySortedOrder(Sort.Direction.DESC), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
