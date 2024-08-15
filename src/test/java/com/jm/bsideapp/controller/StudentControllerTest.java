package com.jm.bsideapp.controller;

import com.jm.bsideapp.model.dto.ApiResponse;
import com.jm.bsideapp.model.dto.StudentDto;
import com.jm.bsideapp.model.entity.Student;
import com.jm.bsideapp.service.IStudent;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class StudentControllerTest {

    @Mock
    private IStudent studentService;

    @InjectMocks
    private StudentController studentController;

    private StudentDto studentDto;
    private Student student;
    private ApiResponse<Student> studentApiResponse;
    private ApiResponse<List<StudentDto>> studentListApiResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

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

        student = Student.builder()
                .id(1)
                .firstName("Julio César")
                .lastName("Matadamas")
                .birthdate("1977-03-08")
                .genre("M")
                .email("julius3877@gmail.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        studentApiResponse = new ApiResponse<>("Estudiante registrado correctamente", Collections.singletonList(student));
        studentListApiResponse = new ApiResponse<>("Estudiantes encontrados", Collections.singletonList(Collections.singletonList(studentDto)));
    }

    @Test
    void create_ShouldCreateStudent() {
        when(studentService.save(any(StudentDto.class))).thenReturn(ResponseEntity.ok(studentApiResponse));

        ResponseEntity<ApiResponse<Student>> response = studentController.create(studentDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiante registrado correctamente", response.getBody().getMessage());
        verify(studentService, times(1)).save(any(StudentDto.class));
    }

    @Test
    void findById_ShouldReturnStudent() {
        when(studentService.findById(anyInt())).thenReturn(ResponseEntity.ok(studentApiResponse));

        ResponseEntity<ApiResponse<Student>> response = studentController.findById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiante registrado correctamente", response.getBody().getMessage());
        verify(studentService, times(1)).findById(1);
    }

    @Test
    void findAll_ShouldReturnAllStudents() {
        when(studentService.findAll()).thenReturn(ResponseEntity.ok(studentListApiResponse));

        ResponseEntity<ApiResponse<List<StudentDto>>> response = studentController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiantes encontrados", response.getBody().getMessage());
        verify(studentService, times(1)).findAll();
    }

    @Test
    void update_ShouldUpdateStudent() {
        when(studentService.save(any(StudentDto.class))).thenReturn(ResponseEntity.ok(studentApiResponse));

        ResponseEntity<ApiResponse<Student>> response = studentController.update(studentDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiante registrado correctamente", response.getBody().getMessage());
        verify(studentService, times(1)).save(any(StudentDto.class));
    }

    @Test
    void delete_ShouldDeleteStudent() {
        when(studentService.delete(anyInt())).thenReturn(ResponseEntity.ok(studentApiResponse));

        ResponseEntity<ApiResponse<Student>> response = studentController.delete(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiante registrado correctamente", response.getBody().getMessage());
        verify(studentService, times(1)).delete(1);
    }

    @Test
    void findByFirstName_ShouldReturnStudents() {
        when(studentService.findByFirstName(anyString())).thenReturn(ResponseEntity.ok(studentListApiResponse));

        ResponseEntity<ApiResponse<List<StudentDto>>> response = studentController.findByFirstName("J");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiantes encontrados", response.getBody().getMessage());
        verify(studentService, times(1)).findByFirstName("J");
    }

    @Test
    void findByLastName_ShouldReturnStudents() {
        when(studentService.findByLastName(anyString())).thenReturn(ResponseEntity.ok(studentListApiResponse));

        ResponseEntity<ApiResponse<List<StudentDto>>> response = studentController.findByLastName("M");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiantes encontrados", response.getBody().getMessage());
        verify(studentService, times(1)).findByLastName("M");
    }

    @Test
    void findByGenre_ShouldReturnStudents() {
        when(studentService.findByGenre(anyString())).thenReturn(ResponseEntity.ok(studentListApiResponse));

        ResponseEntity<ApiResponse<List<StudentDto>>> response = studentController.findByGenre("M");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiantes encontrados", response.getBody().getMessage());
        verify(studentService, times(1)).findByGenre("M");
    }

    @Test
    void findByEmailContaining_ShouldReturnStudents() {
        when(studentService.findByEmailContaining(anyString())).thenReturn(ResponseEntity.ok(studentListApiResponse));

        ResponseEntity<ApiResponse<List<StudentDto>>> response = studentController.findByEmailContaining("gmail");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiantes encontrados", response.getBody().getMessage());
        verify(studentService, times(1)).findByEmailContaining("gmail");
    }

    @Test
    void findByMinimumAge_ShouldReturnStudents() {
        when(studentService.findByMinimumAge(anyInt())).thenReturn(ResponseEntity.ok(studentListApiResponse));

        ResponseEntity<ApiResponse<List<StudentDto>>> response = studentController.findByMinimumAge(46);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Estudiantes encontrados", response.getBody().getMessage());
        verify(studentService, times(1)).findByMinimumAge(46);
    }
}