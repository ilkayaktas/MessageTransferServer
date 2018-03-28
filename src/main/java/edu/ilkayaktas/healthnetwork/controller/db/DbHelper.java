package edu.ilkayaktas.healthnetwork.controller.db;

import edu.ilkayaktas.healthnetwork.controller.db.mongodb.AuthenticationRepository;
import edu.ilkayaktas.healthnetwork.controller.db.mongodb.OnlineUsersRepository;
import edu.ilkayaktas.healthnetwork.controller.db.mongodb.UserRepository;
import edu.ilkayaktas.healthnetwork.model.db.AuthenticationData;
import edu.ilkayaktas.healthnetwork.model.db.OnlineUsers;
import edu.ilkayaktas.healthnetwork.model.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

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
    public AuthenticationData saveAuthenticationData(@NonNull AuthenticationData authenticationData) {
        return authenticationRepository.save(authenticationData);
    }

    @Override
    public AuthenticationData updateAuthenticationData(@NonNull AuthenticationData authenticationData) {
        if(authenticationRepository.existsByUserId(authenticationData.userId)){
            authenticationRepository.deleteByUserId(authenticationData.userId);
        }
        return authenticationRepository.save(authenticationData);
    }

    @Override
    public AuthenticationData getAuthenticationData(@NonNull String userId) {
        return authenticationRepository.findByUserId(userId);
    }

    @Override
    public void deleteAuthenticationData(@NonNull String userId) {
        authenticationRepository.deleteByUserId(userId);
    }

    @Override
    public User saveUser(@NonNull User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(@NonNull User user) {
        if(userRepository.existsByUserId(user.userId)){
            userRepository.deleteByUserId(user.userId);
        }
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUser(@NonNull String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public boolean isUserExist(@NonNull String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public boolean isUserOnline(@NonNull String userId) {
        OnlineUsers onlineUsers = onlineUsersRepository.findByUserId(userId);
        return onlineUsers != null;
    }

    @Override
    public OnlineUsers setUserOnline(@NonNull String userId) {
        onlineUsersRepository.save(new OnlineUsers(userId));
        return null;
    }

    @Override
    public void setUserOffline(@NonNull String userId) {
        onlineUsersRepository.deleteByUserId(userId);
    }

}
