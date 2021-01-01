package com.tenth.scu_score_system.service.impl;

import com.tenth.scu_score_system.Repository.MajorRepository;
import com.tenth.scu_score_system.model.Major;
import com.tenth.scu_score_system.service.MajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "MajorService")
public class MajorServiceImpl implements MajorService {
    @Autowired
    MajorRepository majorRepository;

    @Override
    public List<Major> findAllMajor() {
        return majorRepository.findAllById();
    }

    @Override
    public List<Major> findMajorByDeptId(Integer deptId) {
        return majorRepository.findMajorByDeptId(deptId);
    }
}
