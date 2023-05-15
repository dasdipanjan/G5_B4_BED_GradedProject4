package com.glearning.emp.mgm.ga.lab4.repository;

import com.glearning.emp.mgm.ga.lab4.entity.Employee;
import com.glearning.emp.mgm.ga.lab4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
/**
 * This is employee repository interface which is responsible to do CRUD operation for {@link User} entity.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = ?1")
    User getUserByUsername(String username);
}
