package com.szalajdaniel.medicalclinic.model.dto;

import java.util.List;

public record PageDto<T>(
        List<T> content,
        long totalElements,
        int totalPages,
        int pageNumber
){}
