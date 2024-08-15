package com.jm.bsideapp.service.implementation;

import com.jm.bsideapp.model.dao.StudentDao;
import com.jm.bsideapp.model.dto.ApiResponse;
import com.jm.bsideapp.model.dto.StudentDto;
import com.jm.bsideapp.model.entity.Student;
import com.jm.bsideapp.service.implementation.StudentImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentImplementationTest {
    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentImplementation studentService;

    private Student student;
    private StudentDto studentDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        student = Student.builder()
                .id(1)
                .firstName("Julio César")
                .lastName("Matadamas")
                .birthdate("1977-03-08")
                .genre("M")
                .email("julius@gmail.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        studentDto = StudentDto.builder()
                .id(1)
                .firstName("Julio César")
                .lastName("Matadamas")
                .birthdate("1977-03-08")
                .genre("M")
                .email("julius3877@gmail.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    @Test
    void save_ShouldSaveStudent() {
        when(studentDao.existsByEmail(anyString())).thenReturn(false);
        when(studentDao.save(any(Student.class))).thenReturn(student);

        ResponseEntity<ApiResponse<Student>> response = studentService.save(studentDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiante registrado correctamente.", response.getBody().getMessage());
        assertEquals(1, response.getBody().getData().size());
        assertEquals(student.getId(), response.getBody().getData().getFirst().getId());
    }

    @Test
    void findAll_ShouldReturnAllStudents() {
        List<Student> students = Collections.singletonList(student);
        when(studentDao.findAll()).thenReturn(students);

        ResponseEntity<ApiResponse<List<StudentDto>>> response = studentService.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiantes obtenidos", response.getBody().getMessage());
        assertEquals(1, response.getBody().getData().getFirst().size());
    }

    @Test
    void findById_ShouldReturnStudent() {
        when(studentDao.findById(anyInt())).thenReturn(Optional.of(student));

        ResponseEntity<ApiResponse<Student>> response = studentService.findById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiante encontrado.", response.getBody().getMessage());
        assertEquals(1, response.getBody().getData().size());
        assertEquals(student.getId(), response.getBody().getData().get(0).getId());
    }

    @Test
    void delete_ShouldDeleteStudent() {
        when(studentDao.existsById(anyInt())).thenReturn(true);
        doNothing().when(studentDao).deleteById(anyInt());

        ResponseEntity<ApiResponse<Student>> response = studentService.delete(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().getData().isEmpty());
    }

    @Test
    void findByFirstName_ShouldReturnStudents() {
        List<Student> students = Collections.singletonList(student);
        when(studentDao.findByFirstNameContaining(anyString())).thenReturn(students);

        ResponseEntity<ApiResponse<List<StudentDto>>> response = studentService.findByFirstName("John");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiantes encontrados.", response.getBody().getMessage());
        assertEquals(1, response.getBody().getData().getFirst().size());
    }
}
