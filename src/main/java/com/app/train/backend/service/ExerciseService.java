package com.app.train.backend.service;

import com.app.train.backend.entity.Exercise;
import com.app.train.backend.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    Logger LOGGER = Logger.getLogger(ExerciseService.class.getName());

    public ExerciseService (ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public Exercise findByName(String exerciseName) {
        if (exerciseName.isEmpty()) {
            LOGGER.log(Level.SEVERE, "Exercise Name is empty");
            return null;
        }

        return exerciseRepository.findByName(exerciseName);
    }

    public Exercise findById (Exercise value) {

        if (value == null) {
            return new Exercise();
        }

        return exerciseRepository.findById(value.getId()).orElse(new Exercise());
    }
}
