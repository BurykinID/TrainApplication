package com.app.train.backend.service;

import com.app.train.backend.entity.LevelOfStress;
import com.app.train.backend.repository.StressLevelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelOfStressService {

    private final StressLevelRepository stressLevelRepository;

    public LevelOfStressService (StressLevelRepository stressLevelRepository) {
        this.stressLevelRepository = stressLevelRepository;
    }

    public LevelOfStress getStressLevel(String stressLevelName) {
        if (stressLevelName.isEmpty()) {
            return new LevelOfStress();
        }
        return stressLevelRepository.findByName(stressLevelName);
    }

    public List<LevelOfStress> findAll() {
        return stressLevelRepository.findAll();
    }

}
