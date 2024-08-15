package com.jm.bsideapp.model.dto;

import lombok.*;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ToString
@Builder
@EntityListeners(AuditingEntityListener.class)
public class StudentDto implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String birthdate;
    private String genre;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
