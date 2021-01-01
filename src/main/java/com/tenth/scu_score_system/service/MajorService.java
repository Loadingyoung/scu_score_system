package com.tenth.scu_score_system.service;

import com.tenth.scu_score_system.model.Major;

import java.util.List;

public interface MajorService {
    List<Major> findAllMajor();
    List<Major> findMajorByDeptId(Integer deptId);
}
