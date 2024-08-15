package com.jm.bsideapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
@EntityListeners(AuditingEntityListener.class)
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 45, message = "El nombre debe tener entre 2 y 45 caracteres")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 45, message = "El apellido debe tener entre 2 y 45 caracteres")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha de nacimiento debe estar en el formato YYYY-MM-DD")
    private String birthdate;

    @Pattern(regexp = "^[FM]$", message = "El género debe ser 'F' o 'M'")
    private String genre;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe ser válido")
    private String email;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
