package com.jm.bsideapp.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ApiResponse<T> {
    private String message;
    private List<T> data;

    public ApiResponse(String message, List<T> data) {
        this.message = message;
        this.data = data;
    }

}
