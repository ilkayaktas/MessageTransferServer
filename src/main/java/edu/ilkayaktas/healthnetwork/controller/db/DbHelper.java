package edu.ilkayaktas.healthnetwork.controller.db;

import edu.ilkayaktas.healthnetwork.controller.db.mongodb.OnlineUsersRepository;
import edu.ilkayaktas.healthnetwork.model.OnlineUsers;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by ilkayaktas on 25.03.2018 at 16:00.
 */

public class DbHelper implements IDbHelper {

    @Autowired
    OnlineUsersRepository onlineUsersRepository;

    @PostConstruct
    public void init() {
        System.out.println("DbHelper is constructed!");
    }

    @Override
    public boolean saveUser() {
        System.out.println("User saved!");
        return true;
    }

    @Override
    public boolean isUserOnline(String userId) {
        OnlineUsers onlineUsers = onlineUsersRepository.findByUserId(userId);
        return onlineUsers != null;
    }

    @Override
    public void setUserOnline(String userId) {
        onlineUsersRepository.save(new OnlineUsers(userId));
    }

    @Override
    public void setUserOffline(String userId) {
        onlineUsersRepository.deleteByUserId(userId);
    }

}
