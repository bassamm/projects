package com.sample.trainingacademy.model;

import com.sample.trainingacademy.entity.Training;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record TrainingDao(Long id, String name, String description, String lecturer, BigDecimal price, LocalDate startDate, LocalDate endDate, List<String> bookedCustomer) {
    public static TrainingDao mapFromDto(Training training){
        return TrainingDao.builder()
                .id(training.getId())
                .name(training.getName())
                .price(training.getPrice())
                .description(training.getDescription())
                .lecturer(training.getLecturer())
                .startDate(training.getStartDate().toLocalDate())
                .endDate(training.getEndDate().toLocalDate())
                .bookedCustomer(training.getBookedCustomer())
                .build();
    }
}
