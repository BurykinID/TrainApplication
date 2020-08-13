package com.app.train.backend.repository;

import com.app.train.backend.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {

    @Query("select t from Train t where t.exercise.name like concat('%', :nameOfExercise, '%')")
    List<Train> findByExerciseName (@Param("nameOfExercise") String nameOfExercise);

}
