package com.sample.trainingacademy.controller;

import com.sample.trainingacademy.model.TrainingDao;
import com.sample.trainingacademy.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {
    @Autowired
    private TrainingService service;

    @GetMapping
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TrainingDao> getAllTrainings(){
        return service.getAllAvailableTrainings();
    }

    @GetMapping
    @RequestMapping(value = "/range", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TrainingDao> getTrainingsBetweenTimeRange(
            @RequestParam(value = "start", defaultValue = "1970-01-01") String startDate,
            @RequestParam(value = "end", defaultValue = "1970-01-01") String endDate){
        return service.getTrainingsBetweenTimeRange(startDate, endDate);
    }

    @GetMapping
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TrainingDao> getTrainingByName(@PathVariable(value = "name") String name){
        return service.getTrainingByName(name);
    }

    @GetMapping
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrainingDao> getTrainingById(@PathVariable(value = "id") Long id){
        return service.getTrainingById(id);
    }

    @PostMapping
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> addTraining(@RequestBody TrainingDao training){
        return service.addTraining(training);
    }

    @PutMapping
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> updateTraining(@PathVariable(value = "id") Long id, @RequestBody TrainingDao training){
        return service.updateTraining(id, training);
    }

    @PutMapping
    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public ResponseEntity<HttpStatus> bookTraining(@PathVariable(value = "id") Long id, @RequestParam(value = "customer_name", defaultValue = "UNKOWN") String customerName){
        return service.bookTraining(id, customerName);
    }
}