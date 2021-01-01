package com.tenth.scu_score_system.service.impl;

import com.tenth.scu_score_system.Repository.DepartmentRepository;
import com.tenth.scu_score_system.model.Department;
import com.tenth.scu_score_system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "DepartmentService")
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAllDept() {
        return departmentRepository.findAllById();
    }

    @Override
    public Integer findDeptIdByName(String dept) {
        return departmentRepository.findDepartmentIdByName(dept);
    }
}
