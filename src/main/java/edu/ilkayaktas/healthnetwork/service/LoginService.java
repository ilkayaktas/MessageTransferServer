package edu.ilkayaktas.healthnetwork.service;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by ilkayaktas on 25.03.2018 at 23:53.
 */

@Service
public class LoginService {

    @Autowired
    IDataManager dataManager;

    @PostConstruct
    public void init(){
        System.out.println("LoginService is constructed!");
    }

    public void login(String userId, String token, String expireDateInMilis){
        boolean isSavedSuccesfully = dataManager.saveUser();
        if(isSavedSuccesfully){
            dataManager.setUserOffline(userId);
        }
    }
}
