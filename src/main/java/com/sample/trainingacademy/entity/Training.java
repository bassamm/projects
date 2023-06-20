package com.sample.trainingacademy.entity;

import com.sample.trainingacademy.model.TrainingDao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="training")
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String lecturer;
    private BigDecimal price;
    private Date startDate;
    private Date endDate;
    private List<String> bookedCustomer;
    public void addCustomer(String customerName){
        bookedCustomer.add(customerName);
    }
    public static Training mapFromDao(TrainingDao training){
        return Training.builder()
                .name(training.name())
                .price(training.price())
                .description(training.description())
                .lecturer(training.lecturer())
                .startDate(Date.valueOf(training.startDate()))
                .endDate(Date.valueOf(training.endDate()))
                .bookedCustomer((training.bookedCustomer() == null ? new ArrayList<>() : training.bookedCustomer()))
                .build();
    }

    public void updateContents(TrainingDao training) {
        Predicate<String> nameExistsAndChanged = parameter -> parameter != null && !parameter.equals(this.name);
        Predicate<String> descriptionExistsAndChanged = parameter -> parameter != null && !parameter.equals(this.description);
        Predicate<String> lecturerExistsAndChanged = parameter -> parameter != null && !parameter.equals(this.lecturer);
        Predicate<BigDecimal> priceExistsAndChanged = parameter -> parameter != null && !parameter.equals(this.price);
        Predicate<LocalDate> startDateExistsAndChanged = parameter -> parameter != null && !parameter.equals(this.startDate.toLocalDate());
        Predicate<LocalDate> endDateExistsAndChanged = parameter -> parameter != null && !parameter.equals(this.endDate.toLocalDate());

        if (nameExistsAndChanged.test(training.name())){
            this.name = training.name();
        }
        if (descriptionExistsAndChanged.test(training.description())) {
            this.description = training.description();
        }
        if (lecturerExistsAndChanged.test(training.lecturer())) {
            this.lecturer = training.lecturer();
        }
        if (priceExistsAndChanged.test(training.price())) {
            this.price = training.price();
        }
        if (startDateExistsAndChanged.test(training.startDate())) {
            this.startDate = Date.valueOf(training.startDate());
        }
        if (endDateExistsAndChanged.test(training.endDate())) {
            this.endDate = Date.valueOf(training.endDate());
        }
    }
}
