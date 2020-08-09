package com.app.train.backend.service;

import com.app.train.backend.entity.Exercise;
import com.app.train.backend.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ExerciseService {

    private ExerciseRepository exerciseRepository;

    Logger LOGGER = Logger.getLogger(ExerciseService.class.getName());

    public ExerciseService (ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> findAll() {
        return exerciseRepository.findAll();
    }

    public Exercise findByName(String exerciseName) {
        if (exerciseName.isEmpty() || exerciseName == null) {
            LOGGER.log(Level.SEVERE, "Exercise Name is empty");
            return null;
        }

        Exercise result = exerciseRepository.findByName(exerciseName);

        return result;
    }

    public Exercise findById (Exercise value) {

        if (value == null) {
            return new Exercise();
        }

        Exercise exercise = exerciseRepository.findById(value.getId()).orElse(new Exercise());

        return exercise;
    }
}
