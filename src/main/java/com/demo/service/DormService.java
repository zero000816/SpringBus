package com.demo.service;

import com.demo.entity.Building;
import com.demo.entity.Dorm;

import java.util.List;

public interface DormService {
    public void addBuilding(Building building);
    public List<Building> findAllBuildings();

    public List<Dorm> findAllDormsByBuilding(String building);

    public Building findBuildingByBuildingID(String buildingID);

    public void addOrUpdateDorm(Dorm dorm);

    Dorm findByDid(int id);

    Dorm findByDormName(String name);
}
