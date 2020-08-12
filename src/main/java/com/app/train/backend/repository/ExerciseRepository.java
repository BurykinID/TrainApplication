package com.app.train.backend.repository;

import com.app.train.backend.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;

import java.util.Optional;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

    @Query("select exercise from Exercise exercise where exercise.name like :exerciseName")
    Exercise findByName(@Param("exerciseName")String exerciseName);

    Optional<Exercise> findById(Long id);

}
