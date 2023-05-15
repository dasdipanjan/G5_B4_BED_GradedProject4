package com.glearning.emp.mgm.ga.lab4.repository;

import com.glearning.emp.mgm.ga.lab4.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * This is employee repository interface which is responsible to do CRUD operation for {@link Employee} entity.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByFirstName(String firstName);
}
