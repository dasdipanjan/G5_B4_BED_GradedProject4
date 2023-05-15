package com.glearning.emp.mgm.ga.lab4.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * This is an entity class which is responsible to represent user's role table in database.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@Entity
@Table(name = "TBL_ROLES")
@Data
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
