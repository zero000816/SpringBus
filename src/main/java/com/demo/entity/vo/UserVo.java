package com.demo.entity.vo;

import javafx.scene.effect.SepiaTone;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserVo {
    private String name;
    private List<String> role;
    private String workId;
}
