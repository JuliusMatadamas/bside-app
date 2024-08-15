package com.jm.bsideapp.controller;

import com.jm.bsideapp.model.dto.ApiResponse;
import com.jm.bsideapp.model.dto.StudentDto;
import com.jm.bsideapp.model.entity.Student;
import com.jm.bsideapp.service.IStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StudentController {
    @Autowired
    private IStudent studentService;

    @PostMapping("student")
    public ResponseEntity<ApiResponse<Student>> create(@RequestBody StudentDto studentDto) {
        return studentService.save(studentDto);
    }

    @GetMapping("student/{id}")
    public ResponseEntity<ApiResponse<Student>> findById(@PathVariable Integer id) {
        return studentService.findById(id);
    }

    @GetMapping("students")
    public ResponseEntity<ApiResponse<List<StudentDto>>> findAll() {
        return studentService.findAll();
    }

    @PutMapping("student")
    public ResponseEntity<ApiResponse<Student>> update(@RequestBody StudentDto studentDto) {
        return studentService.save(studentDto);
    }

    @DeleteMapping("student/{id}")
    public ResponseEntity<ApiResponse<Student>> delete(@PathVariable Integer id) {
        return studentService.delete(id);
    }

    @GetMapping("students/firstname/{firstName}")
    public ResponseEntity<ApiResponse<List<StudentDto>>> findByFirstName(@PathVariable String firstName) {
        return studentService.findByFirstName(firstName);
    }

    @GetMapping("students/lastname/{lastName}")
    public ResponseEntity<ApiResponse<List<StudentDto>>> findByLastName(@PathVariable String lastName) {
        return studentService.findByLastName(lastName);
    }

    @GetMapping("students/genre/{genre}")
    public ResponseEntity<ApiResponse<List<StudentDto>>> findByGenre(@PathVariable String genre) {
        return studentService.findByGenre(genre);
    }

    @GetMapping("students/email/{email}")
    public ResponseEntity<ApiResponse<List<StudentDto>>> findByEmailContaining(@PathVariable String email) {
        return studentService.findByEmailContaining(email);
    }

    @GetMapping("students/age/{minimunAge}")
    public ResponseEntity<ApiResponse<List<StudentDto>>> findByMinimumAge(@PathVariable int minimunAge) {
        return studentService.findByMinimumAge(minimunAge);
    }
}
