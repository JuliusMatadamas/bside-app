package com.jm.bsideapp.service.implementation;

import com.jm.bsideapp.model.dto.ApiResponse;
import com.jm.bsideapp.exception.StudentServiceException;
import com.jm.bsideapp.exception.StudentValidationException;
import com.jm.bsideapp.model.dao.StudentDao;
import com.jm.bsideapp.model.dto.StudentDto;
import com.jm.bsideapp.model.entity.Student;
import com.jm.bsideapp.service.IStudent;
import com.jm.bsideapp.validation.StudentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StudentImplementation implements IStudent {
    @Autowired
    private StudentDao studentDao;

    @Override
    public ResponseEntity<ApiResponse<Student>> save(StudentDto studentDto) {
        Student student = Student.builder()
                .id(studentDto.getId())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .birthdate(studentDto.getBirthdate())
                .genre(studentDto.getGenre())
                .email(studentDto.getEmail())
                .build();
        try {
            // Validar los atributos del estudiante
            StudentValidation.validate(student);

            // Verificar si el email ya existe en la base de datos
            if (studentDao.existsByEmail(student.getEmail())) {
                throw new StudentServiceException("El correo electrónico ya está registrado.");
            }

            // Guardar el estudiante
            Student savedStudent = studentDao.save(student);
            Student ss = Student.builder()
                    .id(savedStudent.getId())
                    .firstName(savedStudent.getFirstName())
                    .lastName(savedStudent.getLastName())
                    .birthdate(savedStudent.getBirthdate())
                    .genre(savedStudent.getGenre())
                    .email(savedStudent.getEmail())
                    .createdAt(savedStudent.getCreatedAt())
                    .updatedAt(savedStudent.getUpdatedAt())
                    .build();
            ApiResponse<Student> response = new ApiResponse<>("Estudiante registrado correctamente.", Collections.singletonList(ss));
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (StudentValidationException e) {
            throw new StudentServiceException("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            throw new StudentServiceException("Error al guardar el estudiante: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Student>> update(StudentDto studentDto) {
        Student student = Student.builder()
                .id(studentDto.getId())
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .birthdate(studentDto.getBirthdate())
                .genre(studentDto.getGenre())
                .email(studentDto.getEmail())
                .build();
        try {
            // Validar los atributos del estudiante
            StudentValidation.validate(student);

            // Verificar si el estudiante existe en la base de datos
            if (!studentDao.existsById(student.getId())) {
                throw new StudentServiceException("Estudiante no encontrado con el id " + student.getId());
            }

            // Verificar si el email ya existe en la base de datos (excepto para el mismo estudiante)
            if (studentDao.existsByEmailAndIdNot(student.getEmail(), student.getId())) {
                throw new StudentServiceException("No se puede actualizar la información del estudiante porque el correo electrónico ya está registrado.");
            }

            // Actualizar el estudiante
            Student updatedStudent = studentDao.save(student);
            Student us = Student.builder()
                    .id(updatedStudent.getId())
                    .firstName(updatedStudent.getFirstName())
                    .lastName(updatedStudent.getLastName())
                    .birthdate(updatedStudent.getBirthdate())
                    .genre(updatedStudent.getGenre())
                    .email(updatedStudent.getEmail())
                    .createdAt(updatedStudent.getCreatedAt())
                    .updatedAt(updatedStudent.getUpdatedAt())
                    .build();
            ApiResponse<Student> response = new ApiResponse<>("Estudiante actualizado correctamente.", Collections.singletonList(us));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (StudentValidationException e) {
            throw new StudentServiceException("Error de validación: " + e.getMessage());
        } catch (Exception e) {
            throw new StudentServiceException("Error al guardar el estudiante: " + e.getMessage());
        }
    }


    @Override
    public ResponseEntity<ApiResponse<List<StudentDto>>> findAll() {
        try {
            // Obtener todos los estudiantes
            List<Student> students = (List<Student>) studentDao.findAll();

            // Verificar si la lista está vacía
            if (students.isEmpty()) {
                throw new StudentServiceException("No se encontraron estudiantes");
            }

            // Convertir la lista de Student a StudentDto
            List<StudentDto> studentDtos = students.stream()
                    .map(student -> StudentDto.builder()
                            .id(student.getId())
                            .firstName(student.getFirstName())
                            .lastName(student.getLastName())
                            .birthdate(student.getBirthdate())
                            .genre(student.getGenre())
                            .email(student.getEmail())
                            .createdAt(student.getCreatedAt())
                            .updatedAt(student.getUpdatedAt())
                            .build())
                    .toList();

            // Enviar la respuesta con los datos obtenidos
            ApiResponse<List<StudentDto>> response = new ApiResponse<>("Estudiantes obtenidos", Collections.singletonList(studentDtos));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new StudentServiceException("Error al obtener los estudiantes: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Student>> findById(Integer id) {
        try {
            Student student = studentDao.findById(id).orElseThrow(() -> new StudentServiceException("Estudiante no encontrado con el id " + id));

            Student s = Student.builder()
                    .id(student.getId())
                    .firstName(student.getFirstName())
                    .lastName(student.getLastName())
                    .birthdate(student.getBirthdate())
                    .genre(student.getGenre())
                    .email(student.getEmail())
                    .createdAt(student.getCreatedAt())
                    .updatedAt(student.getUpdatedAt())
                    .build();

            ApiResponse<Student> response = new ApiResponse<>("Estudiante encontrado.", Collections.singletonList(s));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new StudentServiceException("Error al buscar el estudiante: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<Student>> delete(Integer id) {
        try {
            // Verificar si el estudiante existe en la base de datos
            if (!studentDao.existsById(id)) {
                throw new StudentServiceException("Estudiante no encontrado con el id " + id);
            }

            // Eliminar el estudiante
            studentDao.deleteById(id);

            ApiResponse<Student> response = new ApiResponse<>("", Collections.emptyList());
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw new StudentServiceException("Error al eliminar el estudiante: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<StudentDto>>> findByFirstName(String firstName) {
        try {
            List<Student> students = studentDao.findByFirstNameContaining(firstName);

            if (students.isEmpty()) {
                throw new StudentServiceException("No se encontraron estudiantes con el nombre: " + firstName);
            }

            // Convertir la lista de Student a StudentDto
            List<StudentDto> studentDtos = students.stream()
                    .map(student -> StudentDto.builder()
                            .id(student.getId())
                            .firstName(student.getFirstName())
                            .lastName(student.getLastName())
                            .birthdate(student.getBirthdate())
                            .genre(student.getGenre())
                            .email(student.getEmail())
                            .createdAt(student.getCreatedAt())
                            .updatedAt(student.getUpdatedAt())
                            .build())
                    .toList();

            ApiResponse<List<StudentDto>> response = new ApiResponse<>("Estudiantes encontrados.", Collections.singletonList(studentDtos));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new StudentServiceException("Error al buscar estudiantes: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<StudentDto>>> findByLastName(String lastName) {
        try {
            List<Student> students = studentDao.findByLastNameContaining(lastName);

            if (students.isEmpty()) {
                throw new StudentServiceException("No se encontraron estudiantes con el apellido: " + lastName);
            }

            // Convertir la lista de Student a StudentDto
            List<StudentDto> studentDtos = students.stream()
                    .map(student -> StudentDto.builder()
                            .id(student.getId())
                            .firstName(student.getFirstName())
                            .lastName(student.getLastName())
                            .birthdate(student.getBirthdate())
                            .genre(student.getGenre())
                            .email(student.getEmail())
                            .createdAt(student.getCreatedAt())
                            .updatedAt(student.getUpdatedAt())
                            .build())
                    .toList();

            ApiResponse<List<StudentDto>> response = new ApiResponse<>("Estudiantes encontrados.", Collections.singletonList(studentDtos));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw new StudentServiceException("Error al buscar estudiantes: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<StudentDto>>> findByGenre(String genre) {
        try {
            // Se aplica el 'Pattern Matching' de Java 21
            switch (genre) {
                case "F", "M" -> {
                    List<Student> students = studentDao.findByGenre(genre);

                    if (students.isEmpty()) {
                        throw new StudentServiceException("No se encontraron estudiantes con el género: " + genre);
                    }

                    // Convertir la lista de Student a StudentDto
                    List<StudentDto> studentDtos = students.stream()
                            .map(student -> StudentDto.builder()
                                    .id(student.getId())
                                    .firstName(student.getFirstName())
                                    .lastName(student.getLastName())
                                    .birthdate(student.getBirthdate())
                                    .genre(student.getGenre())
                                    .email(student.getEmail())
                                    .createdAt(student.getCreatedAt())
                                    .updatedAt(student.getUpdatedAt())
                                    .build())
                            .toList();

                    ApiResponse<List<StudentDto>> response = new ApiResponse<>("Estudiantes encontrados.", Collections.singletonList(studentDtos));
                    return new ResponseEntity<>(response, HttpStatus.OK);
                }
                default -> throw new StudentServiceException("El género debe ser 'F' o 'M'.");
            }
        } catch (Exception e) {
            throw new StudentServiceException("Error al buscar estudiantes: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<StudentDto>>> findByEmailContaining(String email) {
        try {
            List<Student> students = studentDao.findByEmailContaining(email);

            if (students.isEmpty()) {
                throw new StudentServiceException("No se encontraron estudiantes cuyo correo electrónico contenga: " + email);
            }

            // Convertir la lista de Student a StudentDto
            List<StudentDto> studentDtos = students.stream()
                    .map(student -> StudentDto.builder()
                            .id(student.getId())
                            .firstName(student.getFirstName())
                            .lastName(student.getLastName())
                            .birthdate(student.getBirthdate())
                            .genre(student.getGenre())
                            .email(student.getEmail())
                            .createdAt(student.getCreatedAt())
                            .updatedAt(student.getUpdatedAt())
                            .build())
                    .toList();

            ApiResponse<List<StudentDto>> response = new ApiResponse<>("Estudiantes encontrados.", Collections.singletonList(studentDtos));
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            throw new StudentServiceException("Error al buscar estudiantes: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<ApiResponse<List<StudentDto>>> findByMinimumAge(int minimumAge) {
        try {
            // Validar que el parámetro sea un entero positivo
            if (minimumAge < 0) {
                throw new StudentServiceException("La edad mínima debe ser un número positivo.");
            }

            // Obtener todos los estudiantes
            List<Student> allStudents = (List<Student>) studentDao.findAll();

            // Filtrar los estudiantes cuya edad sea mayor o igual al valor dado empleando del Stream API de Java 21 la función filter con un predicado lambda para simplificar la lógica de filtrado
            List<Student> filteredStudents = StreamSupport.stream(studentDao.findAll().spliterator(), false)
                    .filter(student -> calculateAge(student.getBirthdate()) >= minimumAge)
                    .toList();

            if (filteredStudents.isEmpty()) {
                throw new StudentServiceException("No se encontraron estudiantes con edad mayor o igual a " + minimumAge);
            }

            // Convertir la lista de Student a StudentDto
            List<StudentDto> studentDtos = filteredStudents.stream()
                    .map(student -> StudentDto.builder()
                            .id(student.getId())
                            .firstName(student.getFirstName())
                            .lastName(student.getLastName())
                            .birthdate(student.getBirthdate())
                            .genre(student.getGenre())
                            .email(student.getEmail())
                            .createdAt(student.getCreatedAt())
                            .updatedAt(student.getUpdatedAt())
                            .build())
                    .toList();

            ApiResponse<List<StudentDto>> response = new ApiResponse<>("Estudiantes encontrados.", Collections.singletonList(studentDtos));
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            throw new StudentServiceException("Error al buscar estudiantes: " + e.getMessage());
        }
    }

    // Método para calcular la edad a partir de la fecha de nacimiento
    private int calculateAge(String birthdate) {
        LocalDate birthDate = LocalDate.parse(birthdate);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
