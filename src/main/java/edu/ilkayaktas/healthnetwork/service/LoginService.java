package edu.ilkayaktas.healthnetwork.service;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.db.OnlineUsers;
import edu.ilkayaktas.healthnetwork.model.db.User;
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
        User user = dataManager.saveUser(new User());
        if(user != null){
            OnlineUsers onlineUser = dataManager.setUserOnline(userId);
        }
    }

    public void logout(String userId){
        dataManager.setUserOffline(userId);
    }
}
