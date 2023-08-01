/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.azrul.langkuik.loanorigsystem;

import com.azrul.langkuik.framework.dao.DataAccessObject;
import com.azrul.langkuik.framework.rule.SoundnessCheck;
import com.azrul.langkuik.views.common.MainView;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author azrul
 */
@Component
public class Init {
    @Autowired
    DataAccessObject dao;
    
    @Autowired
    SoundnessCheck soundnessCheck;

    @PostConstruct
    public void run() {
       var errors = soundnessCheck.runChecks();
        Logger logger = Logger.getLogger(MainView.class.getName());
        for (var error:errors.entrySet()){
            logger.log(Level.SEVERE,error.getKey()+":"+error.getValue());
        }
    }
}
