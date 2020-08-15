package com.app.train.backend.repository;

import com.app.train.backend.entity.Train;
import com.app.train.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {

    @Query("select t from Train t where t.exercise.name like concat('%', :nameOfExercise, '%')")
    List<Train> findByExerciseName (@Param("nameOfExercise") String nameOfExercise);

    @Query("select count(t) from Train t where (t.idUser = :idUser) " +
            "and (t.exercise.name = :exerciseName) " +
            "and (t.date = :startDay)")
    Optional<Double> findSet (@Param("idUser") User idUser,
                              @Param("exerciseName") String exerciseName,
                              @Param("startDay") LocalDate dateStart);

    @Query("select t.repeats from Train t where (t.idUser = :idUser)" +
            "and (t.exercise.name = :exerciseName)" +
            "and (t.date = :startDay)" +
            "and (t.set = :set)")
    Optional<Double> findRepeats (@Param("idUser") User idUser,
                                  @Param("exerciseName") String exerciseName,
                                  @Param("startDay") LocalDate startDay,
                                  @Param("set") int set);

    @Query("select t.weight from Train t where (t.idUser = :idUser)" +
            "and (t.exercise.name = :exerciseName)" +
            "and (t.date = :startDay)" +
            "and (t.set = :set)")
    Optional<Double> findWeight (@Param("idUser") User idUser,
                                 @Param("exerciseName") String exerciseName,
                                 @Param("startDay") LocalDate startDay,
                                 @Param("set") int set);

    @Query("select t.levelOfStress.name from Train t where (t.idUser = :idUser)" +
            "and (t.exercise.name = :exerciseName)" +
            "and (t.date = :startDay)" +
            "and (t.set = :set)")
    Optional<String> findLevelOfStress (@Param("idUser") User idUser,
                                        @Param("exerciseName") String exerciseName,
                                        @Param("startDay") LocalDate startDay,
                                        @Param("set") int set);

    @Query("select t.timeRecreation from Train t where (t.idUser = :idUser)" +
            "and (t.exercise.name = :exerciseName)" +
            "and (t.date = :startDay)" +
            "and (t.set = :set)")
    Optional<Double> findTimeRecreation (@Param("idUser") User idUser,
                                         @Param("exerciseName") String exerciseName,
                                         @Param("startDay") LocalDate startDay,
                                         @Param("set") int set);
}
