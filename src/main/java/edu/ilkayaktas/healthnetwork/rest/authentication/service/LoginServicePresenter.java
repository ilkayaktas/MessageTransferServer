package edu.ilkayaktas.healthnetwork.rest.authentication.service;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.db.AuthenticationData;
import edu.ilkayaktas.healthnetwork.model.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by ilkayaktas on 25.03.2018 at 23:53.
 */

@Service
public class LoginServicePresenter {

    private final IDataManager dataManager;

    @Autowired
    public LoginServicePresenter(IDataManager dataManager) {
        this.dataManager = dataManager;
    }

    @PostConstruct
    public void init(){
        System.out.println("LoginService is constructed!");
    }

    public void login(User user, String userId, String token, String expireDate){
        User usr = dataManager.upsertUser(user);
        if(usr != null){
            dataManager.upsertAuthenticationData(new AuthenticationData(userId, token, expireDate));
            dataManager.setUserOnline(userId);
        }
    }

    public void logout(String userId){
        dataManager.setUserOffline(userId);
    }
}
