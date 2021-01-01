package com.tenth.scu_score_system;

import com.tenth.scu_score_system.Repository.CourseRepository;
import com.tenth.scu_score_system.Repository.StudentRepository;
import com.tenth.scu_score_system.Repository.TeachingRepository;
import com.tenth.scu_score_system.model.*;
import com.tenth.scu_score_system.service.SelectCourseService;
import com.tenth.scu_score_system.service.StudentService;
import com.tenth.scu_score_system.service.TeachingService;
import com.tenth.scu_score_system.service.impl.TeachingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class ScuScoreSystemApplicationTests {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    SelectCourseService selectCourseService;

    @Test
    void contextLoads() {
    }
}
