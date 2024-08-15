package com.jm.bsideapp.repository;

import com.jm.bsideapp.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
