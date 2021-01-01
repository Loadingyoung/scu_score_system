package com.tenth.scu_score_system.service;

import com.tenth.scu_score_system.model.Administrator;

public interface AdministratorService {
    Integer countAll();
    String findAdminPswdById(Integer id);
    String findAdminNameById(Integer id);
    void updatePasswd(Administrator administrator);
    boolean confirmPasswd(Integer aid,String pswd);
}
