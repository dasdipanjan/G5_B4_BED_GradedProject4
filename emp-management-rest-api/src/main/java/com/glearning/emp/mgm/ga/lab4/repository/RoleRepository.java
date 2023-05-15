package com.glearning.emp.mgm.ga.lab4.repository;

import com.glearning.emp.mgm.ga.lab4.entity.Employee;
import com.glearning.emp.mgm.ga.lab4.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * This is employee repository interface which is responsible to do CRUD operation for {@link Role} entity.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {


}
