package com.demo.service.impl;

import com.demo.entity.ChooseLog;
import com.demo.repository.ChooseLogRepository;
import com.demo.service.ChooseLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChooseLogServiceImpl implements ChooseLogService {

    @Autowired
    ChooseLogRepository chooseLogRepository;

    @Override
    public ChooseLog findByStudentID(String studentID) {
        return chooseLogRepository.findAllByStudentID(studentID);
    }
}
