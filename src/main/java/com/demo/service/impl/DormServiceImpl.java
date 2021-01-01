package com.demo.service.impl;

import com.demo.entity.Building;
import com.demo.entity.Dorm;
import com.demo.repository.BuildingRepository;
import com.demo.repository.DormRepository;
import com.demo.service.DormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormServiceImpl implements DormService {
    @Autowired
    BuildingRepository buildingRepository;

    @Autowired
    DormRepository dormRepository;

    @Override
    public List<Dorm> findAllDormsByBuilding(String building) {
        return dormRepository.findAllByBuilding(building);

    }

    @Override
    public void addBuilding(Building building) {

    }

    @Override
    public List<Building> findAllBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public Building findBuildingByBuildingID(String buildingID) {
        return null;
    }

    @Override
    public void addOrUpdateDorm(Dorm dorm) {

    }

    @Override
    public Dorm findByDid(int id) {
        return dormRepository.findByDid(id);
    }

    @Override
    public Dorm findByDormName(String name) {
        return dormRepository.findByDormName(name);
    }
}
