package com.demo.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class ChooseForm {
    String buildingID;
    String firstStudent;
    List<String> studentIDs;
    int gender;
}
