package com.jm.bsideapp.validation;

import com.jm.bsideapp.exception.StudentValidationException;
import com.jm.bsideapp.model.entity.Student;

public class StudentValid {
    public static void validate(Student student) throws StudentValidationException {
        if (student == null) {
            throw new StudentValidationException("La información del estudiante no puede estar vacía");
        }

        if (student.getFirstName() == null || student.getFirstName().trim().isEmpty()) {
            throw new StudentValidationException("El nombre es requerido");
        }

        if (student.getLastName() == null || student.getLastName().trim().isEmpty()) {
            throw new StudentValidationException("El apellido es requerido");
        }

        if (student.getBirthdate() == null) {
            throw new StudentValidationException("La fecha de nacimiento es requerida");
        }

        if (!student.getEmail().matches(String.valueOf(StudentValidation.EMAIL_PATTERN))) {
            throw new StudentValidationException("El correo electrónico no tiene un formato válido o está vacío.");
        }

        if (student.getGenre() == null || student.getGenre().trim().isEmpty()) {
            throw new StudentValidationException("El genero es requerido");
        }
    }
}
