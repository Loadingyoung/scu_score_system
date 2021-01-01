package com.tenth.scu_score_system.service.impl;

import com.tenth.scu_score_system.Repository.AdministratorRepository;
import com.tenth.scu_score_system.model.Administrator;
import com.tenth.scu_score_system.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "AdministratorService")
public class AdministratorServiceImpl implements AdministratorService{
    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Integer countAll(){
        return administratorRepository.countAll();
    }
    @Override
    public String findAdminPswdById(Integer id) {
        return administratorRepository.findAdministratorByAdminId(id);
    }

    @Override
    public String findAdminNameById(Integer id) {
        return administratorRepository.findAdminNameById(id);
    }

    @Override
    public void updatePasswd(Administrator administrator) {
        administratorRepository.saveAndFlush(administrator);
    }

    @Override
    public boolean confirmPasswd(Integer aid, String pswd) {
        if (pswd.equals(findAdminPswdById(aid))){
            return true;
        }else {
            return false;
        }
    }
}
