package com.app.train.backend.service;

import com.app.train.backend.entity.Train;
import com.app.train.backend.repository.ExerciseRepository;
import com.app.train.backend.repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TrainService {

    private static final Logger LOGGER = Logger.getLogger(TrainService.class.getName());
    private final TrainRepository trainRepository;
    private final ExerciseRepository exerciseRepository;

    public TrainService (TrainRepository trainRepository, ExerciseRepository exerciseRepository) {
        this.trainRepository = trainRepository;
        this.exerciseRepository = exerciseRepository;
    }

    public List<Train> findAll() {
        return trainRepository.findAll();
    }

    public List<Train> findAll(String filter) {
        if (filter.isEmpty()) {
            return trainRepository.findAll();
        }

        return trainRepository.findByExerciseName(filter);
    }

    public long count() {
        return trainRepository.count();
    }

    public void save(Train train) {
        if (train != null)
            trainRepository.save(train);
        else {
            LOGGER.log(Level.SEVERE, "Train is null.");
        }
    }

    public void delete(Train train) {
        trainRepository.delete(train);
    }

}
