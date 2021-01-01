package com.tenth.scu_score_system.service.impl;

import com.tenth.scu_score_system.Repository.TitleRepository;
import com.tenth.scu_score_system.model.Title;
import com.tenth.scu_score_system.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "TitleService")
public class TitleServiceImpl implements TitleService {
    @Autowired
    TitleRepository titleRepository;

    @Override
    public List<Title> findAllTitle() {
        return titleRepository.findAllById();
    }
}
