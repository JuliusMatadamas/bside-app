package com.jm.bsideapp.service;

import com.jm.bsideapp.model.dto.ApiResponse;
import com.jm.bsideapp.model.dto.StudentDto;
import com.jm.bsideapp.model.entity.Student;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStudent {
    ResponseEntity<ApiResponse<Student>> save(StudentDto student);

    ResponseEntity<ApiResponse<Student>> update(StudentDto student);

    ResponseEntity<ApiResponse<List<StudentDto>>> findAll();

    ResponseEntity<ApiResponse<Student>> findById(Integer id);

    ResponseEntity<ApiResponse<Student>> delete(Integer id);

    ResponseEntity<ApiResponse<List<StudentDto>>> findByFirstName(String firstName);

    ResponseEntity<ApiResponse<List<StudentDto>>> findByLastName(String firstName);

    ResponseEntity<ApiResponse<List<StudentDto>>> findByGenre(String genre);

    ResponseEntity<ApiResponse<List<StudentDto>>> findByEmailContaining(String email);

    ResponseEntity<ApiResponse<List<StudentDto>>> findByMinimumAge(int minimumAge);
}
