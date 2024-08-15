package com.jm.bsideapp.model.dao;

import com.jm.bsideapp.model.entity.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentDao extends CrudRepository<Student, Integer> {
    boolean existsByEmail(String email);
    List<Student> findByFirstNameContaining(String firstName);
    List<Student> findByLastNameContaining(String lastName);
    List<Student> findByGenre(String genre);
    List<Student> findByEmailContaining(String email);

    boolean existsByEmailAndIdNot(String email, Integer id);
}
