package com.sample.trainingacademy.service;

import com.sample.trainingacademy.repository.TrainingRepository;
import com.sample.trainingacademy.entity.Training;
import com.sample.trainingacademy.model.TrainingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainingService {
    private TrainingRepository repository;
    private DateFormatter dateFormatter;
    /**
     * pattern for propper formation of dateTime in the rm-backend
     */
    public static final String datePattern = "yyyy-MM-dd";

    @Autowired
    public TrainingService(TrainingRepository repository){
        this.repository = repository;
        dateFormatter = new DateFormatter(datePattern);
    }

    public List<TrainingDao> getAllAvailableTrainings(){
        return repository.findAll().stream()
                .map(TrainingDao::mapFromDto)
                .collect(Collectors.toList());
    }

    public List<TrainingDao> getTrainingsBetweenTimeRange(String startDate, String endDate){
        try {
            Date start = Date.valueOf(startDate);
            Date end = Date.valueOf(endDate);
            return repository.findAllBetweenTimeframe(start, end).stream()
                    .map(TrainingDao::mapFromDto)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }

    }

    public ResponseEntity<HttpStatus> addTraining(TrainingDao training) {
        List<Training> existingTraining = repository.findAllByNameWithExactTime(training.name(), Date.valueOf(training.startDate()), Date.valueOf(training.endDate()));
        if(!existingTraining.isEmpty()){
            return ResponseEntity.ok(HttpStatus.FORBIDDEN);
        }
        repository.save(Training.mapFromDao(training));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateTraining(Long id, TrainingDao training) {
        Optional<Training> existingTraining = repository.findById(id);
        if(existingTraining.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        existingTraining.get().updateContents(training);
        repository.save(existingTraining.get());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public List<TrainingDao> getTrainingByName(String name) {
        return repository.findAllByName(name).stream()
                .map(TrainingDao::mapFromDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<TrainingDao> getTrainingById(Long id) {
        Optional<TrainingDao> training = repository.findById(id).map(TrainingDao::mapFromDto);
        if(training.isPresent()){
            return new ResponseEntity<>(training.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<HttpStatus> bookTraining(long id, String customerName) {
        Optional<Training> existingTraining = repository.findById(id);
        var statusWrapper = new Object(){ ResponseEntity<HttpStatus> status = ResponseEntity.notFound().build(); };

        existingTraining.filter(training -> training.getBookedCustomer().size() < 10)
                        .filter(training -> training.getBookedCustomer().stream().noneMatch(c -> c.equals(customerName)))
                        .ifPresent(training -> {
                            training.addCustomer(customerName);
                            repository.save(training);
                            statusWrapper.status = ResponseEntity.ok(HttpStatus.OK);
                        });
        return statusWrapper.status;
    }
}
