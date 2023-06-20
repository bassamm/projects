package com.sample.trainingacademy.service;

import com.sample.trainingacademy.common.Store;
import com.sample.trainingacademy.model.TrainingDao;
import com.sample.trainingacademy.repository.TrainingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class TrainingServiceTest {
    @Mock
    TrainingRepository repo;

    @InjectMocks
    TrainingService service;

    @Test
    public void whenGetAllTrainings_thenOK() {
        given(repo.findAll()).willReturn(Store.savedTrainings);
        List<TrainingDao> availableTrainings = service.getAllAvailableTrainings();
        assertFalse(availableTrainings.isEmpty());
        assertEquals(Store.savedTrainings.size(), availableTrainings.size());
    }

    @Test
    public void whenGetTrainingBetweenTimeRange_thenOK() {
        Date start = Date.valueOf("2023-01-01"), end = Date.valueOf("2023-01-15");
        when(repo.findAllBetweenTimeframe(start, end))
                .thenReturn(Store.savedTrainings
                        .stream()
                        .filter(t -> ((t.getStartDate().equals(start) || t.getStartDate().before(start)) &&
                                (t.getEndDate().equals(start) || t.getEndDate().after(start))) ||
                                ((t.getStartDate().equals(end) || t.getStartDate().before(end)) &&
                                        (t.getEndDate().equals(end) || t.getEndDate().after(end)))).collect(Collectors.toList()));

        List<TrainingDao> trainingsInRange = service.getTrainingsBetweenTimeRange("2023-01-01", "2023-01-15");
        assertFalse(trainingsInRange.isEmpty());
        assertEquals(1L, trainingsInRange.get(0).id());
        assertEquals(2L, trainingsInRange.get(1).id());
    }

    @Test
    public void whenGetTrainingBetweenInvalidTimeRange_thenOK() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            service.getTrainingsBetweenTimeRange("2023-01-100", "2023-100-15");
        });
    }

    @Test
    public void whenUpdateTraining_thenOK() {
        when(repo.findById(1L)).thenReturn(Optional.of(Store.savedTrainings.get(0)));
        TrainingDao update = TrainingDao.builder()
                .startDate(LocalDate.of(2023,1,1))
                .endDate(LocalDate.of(2023, 3, 30)).build();

        assertEquals(HttpStatus.OK, service.updateTraining(1L, update).getBody());
    }

    @Test
    public void whenUpdateTraining_thenNotOK() {
        when(repo.findById(100L)).thenReturn(Optional.empty());
        TrainingDao update = TrainingDao.builder().build();
        var status = service.updateTraining(100L, update);
        assertEquals(HttpStatus.NOT_FOUND, status.getStatusCode());
    }

    @Test
    public void whenUpdateTrainingWithAlreadyMaxNumberOfBookedCustomers_thenNotOK() {
        Store.savedTrainings.get(0).setBookedCustomer(Store.MAX_CUSTOMERS);
        when(repo.findById(1L)).thenReturn(Optional.of(Store.savedTrainings.get(0)));
        var status = service.bookTraining(1L, "new_customer");
        assertEquals(HttpStatus.NOT_FOUND, status.getStatusCode());
    }
}
