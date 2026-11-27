package com.szalajdaniel.medicalclinic.mapper;

import com.szalajdaniel.medicalclinic.model.dto.PageDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public final class PageMapper {

    public static <T> PageDto<T> toPageDto(Page<T> page) {
        return new PageDto<>(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber()
        );
    }
}