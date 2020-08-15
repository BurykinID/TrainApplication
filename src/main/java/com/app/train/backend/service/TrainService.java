package com.app.train.backend.service;

import com.app.train.backend.entity.Train;
import com.app.train.backend.entity.User;
import com.app.train.backend.repository.ExerciseRepository;
import com.app.train.backend.repository.TrainRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TrainService {

    private static final Logger LOGGER = Logger.getLogger(TrainService.class.getName());
    private final TrainRepository trainRepository;

    public TrainService (TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
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

    public Double findSet (User idUser, String nameExercise, LocalDate startDay) {

        return trainRepository.findSet(idUser, nameExercise, startDay).orElse(1.0);

    }

    public Double findRepeats (User idUser, String nameExercise, LocalDate startDay, int set) {

        return trainRepository.findRepeats(idUser, nameExercise, startDay, set).orElse(0.0);

    }

    public Double findWeight (User idUser, String nameExercise, LocalDate startDay, int set) {

        return trainRepository.findWeight(idUser, nameExercise, startDay, set).orElse(0.0);

    }

    public String findLevelOfStress (User idUser, String nameExercise, LocalDate startDay, int exerciseSet) {

        return trainRepository.findLevelOfStress(idUser, nameExercise, startDay, exerciseSet).orElse("");

    }

    public Double findTimeRecreation (User idUser, String nameExercise, LocalDate startDay, int exerciseSet) {

        return trainRepository.findTimeRecreation(idUser, nameExercise, startDay, exerciseSet).orElse(0.0);

    }

}
