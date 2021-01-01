package com.tenth.scu_score_system.service;

import com.tenth.scu_score_system.model.Department;
import com.tenth.scu_score_system.service.impl.DepartmentServiceImpl;

import java.util.List;

public interface DepartmentService {
    List<Department> findAllDept();

    Integer findDeptIdByName(String dept);
}
