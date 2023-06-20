package com.sample.trainingacademy.common;

import com.sample.trainingacademy.entity.Training;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public final class Store {
    public static final List<String> CUSTOMERS = List.of("Test_customer");
    public static final List<String> MAX_CUSTOMERS = List.of(
            "Test_customer1",
            "Test_customer2",
            "Test_customer3",
            "Test_customer4",
            "Test_customer5",
            "Test_customer6",
            "Test_customer7",
            "Test_customer8",
            "Test_customer9",
            "Test_customer10");
    public static final List<Training> savedTrainings = List.of(
            Training.builder()
                    .id(1L)
                    .name("TestTraining1")
                    .description("TestDescription1")
                    .lecturer("TestLecturer1")
                    .price(BigDecimal.ONE)
                    .startDate(Date.valueOf("2023-01-01"))
                    .endDate(Date.valueOf("2023-01-30"))
                    .bookedCustomer(CUSTOMERS).build(),
            Training.builder()
                    .id(2L)
                    .name("TestTraining2")
                    .description("TestDescription2")
                    .lecturer("TestLecturer2")
                    .price(BigDecimal.ONE)
                    .startDate(Date.valueOf("2023-01-15"))
                    .endDate(Date.valueOf("2023-02-14"))
                    .bookedCustomer(CUSTOMERS).build(),
            Training.builder()
                    .id(3L)
                    .name("TestTraining3")
                    .description("TestDescription3")
                    .lecturer("TestLecturer3")
                    .price(BigDecimal.ONE)
                    .startDate(Date.valueOf("2023-02-01"))
                    .endDate(Date.valueOf("2023-02-28"))
                    .bookedCustomer(CUSTOMERS).build(),
            Training.builder()
                    .id(4L)
                    .name("TestTraining4")
                    .description("TestDescription4")
                    .lecturer("TestLecturer4")
                    .price(BigDecimal.ONE)
                    .startDate(Date.valueOf("2023-02-15"))
                    .endDate(Date.valueOf("2023-03-14"))
                    .bookedCustomer(CUSTOMERS).build()
    );
}
