package com.app.train.backend.repository;

import com.app.train.backend.entity.LevelOfStress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StressLevelRepository extends JpaRepository<LevelOfStress, Long> {

    LevelOfStress findByName (String stressLevelName);
}
