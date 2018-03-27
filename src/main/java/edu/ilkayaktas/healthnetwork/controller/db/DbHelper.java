package edu.ilkayaktas.healthnetwork.controller.db;

import edu.ilkayaktas.healthnetwork.controller.db.mongodb.AuthenticationRepository;
import edu.ilkayaktas.healthnetwork.controller.db.mongodb.OnlineUsersRepository;
import edu.ilkayaktas.healthnetwork.controller.db.mongodb.UserRepository;
import edu.ilkayaktas.healthnetwork.model.db.AuthenticationData;
import edu.ilkayaktas.healthnetwork.model.db.OnlineUsers;
import edu.ilkayaktas.healthnetwork.model.db.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by ilkayaktas on 25.03.2018 at 16:00.
 */

public class DbHelper implements IDbHelper {

    @Autowired
    OnlineUsersRepository onlineUsersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @PostConstruct
    public void init() {
        System.out.println("DbHelper is constructed!");
    }

    @Override
    public AuthenticationData saveAuthenticationData(AuthenticationData authenticationData) {
        return authenticationRepository.save(authenticationData);
    }

    @Override
    public AuthenticationData getAuthenticationData(String userId) {
        return authenticationRepository.findByUserId(userId);
    }

    @Override
    public void deleteAuthenticationData(String userId) {
        authenticationRepository.deleteByUserId(userId);
    }

    @Override
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public boolean isUserExist(String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public boolean isUserOnline(String userId) {
        OnlineUsers onlineUsers = onlineUsersRepository.findByUserId(userId);
        return onlineUsers != null;
    }

    @Override
    public OnlineUsers setUserOnline(String userId) {
        onlineUsersRepository.save(new OnlineUsers(userId));
        return null;
    }

    @Override
    public void setUserOffline(String userId) {
        onlineUsersRepository.deleteByUserId(userId);
    }

}
