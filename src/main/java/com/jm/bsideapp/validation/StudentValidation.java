package com.jm.bsideapp.validation;

import com.jm.bsideapp.exception.StudentValidationException;
import com.jm.bsideapp.model.dto.StudentDto;
import com.jm.bsideapp.model.entity.Student;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class StudentValidation {
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ ]{2,45}$");
    public static final Pattern EMAIL_PATTERN = Pattern.compile(".+@.+\\..{2,}");

    public static void validate(Student student) {
        validateName(student.getFirstName(), "El nombre");
        validateName(student.getLastName(), "El apellido");
        validateBirthdate(student.getBirthdate());
        validateGenre(student.getGenre());
        validateEmail(student.getEmail());
    }

    private static void validateName(String name, String field) {
        if (name == null || name.trim().isEmpty()) {
            throw new StudentValidationException(field + " es obligatorio.");
        }
        if (name.trim().length() < 2 || name.trim().length() > 45) {
            throw new StudentValidationException(field + " debe tener entre 2 y 45 caracteres, no se permiten números ni caracteres especiales en este campo.");
        }
        if (!NAME_PATTERN.matcher(name).matches()) {
            throw new StudentValidationException(field + " debe tener entre 2 y 45 caracteres, no se permiten números ni caracteres especiales en este campo.");
        }
    }

    private static void validateBirthdate(String birthdate) {
        if (birthdate == null) {
            throw new StudentValidationException("La fecha de nacimiento del estudiante es obligatoria.");
        }
        int age = Period.between(LocalDate.parse(birthdate), LocalDate.now()).getYears();
        System.out.println(age);
        if (age < 6 || age > 65) {
            throw new StudentValidationException("La edad del estudiante debe estar entre 6 y 65 años.");
        }
    }

    private static void validateGenre(String genre) {
        if (genre == null || genre.isBlank()) {
            throw new StudentValidationException("El género es obligatorio.");
        }
        if (!genre.equals("F") && !genre.equals("M")) {
            throw new StudentValidationException("El género debe ser 'F' o 'M'.");
        }
    }

    private static void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new StudentValidationException("El correo electrónico es obligatorio.");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new StudentValidationException("El formato del correo electrónico no es válido o el dominio debe tener al menos 2 letras.");
        }
    }
}
