package edu.ilkayaktas.healthnetwork.controller;

import edu.ilkayaktas.healthnetwork.controller.api.IApiHelper;
import edu.ilkayaktas.healthnetwork.controller.db.IDbHelper;
import edu.ilkayaktas.healthnetwork.model.AppConstants;
import edu.ilkayaktas.healthnetwork.model.db.AuthenticationData;
import edu.ilkayaktas.healthnetwork.model.db.OnlineUsers;
import edu.ilkayaktas.healthnetwork.model.db.User;
import edu.ilkayaktas.healthnetwork.model.rest.AuthorizationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;

/**
 * Created by ilkayaktas on 25.03.2018 at 16:02.
 */

public class DataManager implements IDataManager{

    @Autowired
    private IDbHelper dbHelper;

    @Autowired
    private IApiHelper apiHelper;

    @PostConstruct
    public void init() {
        System.out.println("DataManager is constructed!");
    }


    @Override
    public AuthenticationData saveAuthenticationData(AuthenticationData authenticationData) {
        return dbHelper.saveAuthenticationData(authenticationData);
    }

    @Override
    public AuthenticationData updateAuthenticationData(AuthenticationData authenticationData) {
        return dbHelper.updateAuthenticationData(authenticationData);
    }

    @Override
    public AuthenticationData getAuthenticationData(String userId) {
        return dbHelper.getAuthenticationData(userId);
    }

    @Override
    public void deleteAuthenticationData(String userId) {
        dbHelper.deleteAuthenticationData(userId);
    }

    @Override
    public User saveUser(User user) {
        dbHelper.saveUser(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        dbHelper.updateUser(user);
        return user;
    }

    @Override
    public User getUser(String userId) {
        return dbHelper.getUser(userId);
    }

    @Override
    public boolean isUserExist(String userId) {
        return dbHelper.isUserExist(userId);
    }

    @Override
    public boolean isUserOnline(String userId) {
        return dbHelper.isUserOnline(userId);
    }

    @Override
    public OnlineUsers setUserOnline(String userId) {
        dbHelper.setUserOnline(userId);
        return null;
    }

    @Override
    public void setUserOffline(String userId) {
        dbHelper.setUserOffline(userId);
    }

    @Override
    public ResponseEntity<AuthorizationData> isRequestAuthorized(String userId, String token) {
        User user = getUser(userId);

        // user exists
        if(user != null){

            // user is online
            if(isUserOnline(userId)){
                AuthenticationData authenticationData = getAuthenticationData(userId);

                // authentication data is available
                if(authenticationData != null){

                    // token is expired
                    if (isExpired(Long.valueOf(authenticationData.expireDate))){
                        return new ResponseEntity<>(new AuthorizationData(AppConstants.HTTP_STATUS_UNAUTHORIZED,
                                -1,
                                "Token is expired! Login again."),
                                AppConstants.HTTP_STATUS_UNAUTHORIZED);
                    }

                    // token is equal
                    if(authenticationData.token.equals(token)){
                        return new ResponseEntity<>(new AuthorizationData(AppConstants.HTTP_STATUS_OK,
                                1,
                                "\"Well done, Authorized!\""),
                                AppConstants.HTTP_STATUS_OK);
                    }

                    return new ResponseEntity<>(new AuthorizationData(AppConstants.HTTP_STATUS_UNAUTHORIZED,
                            0,
                            "Token is not valid!"),
                            AppConstants.HTTP_STATUS_UNAUTHORIZED);

                }

            }
        }

        return new ResponseEntity<>(new AuthorizationData(AppConstants.HTTP_STATUS_UNAUTHORIZED,
                0,
                "Unauthorized request!"),
                AppConstants.HTTP_STATUS_UNAUTHORIZED);
        
    }

    public boolean isExpired(Long expireDate){
        Long now = System.currentTimeMillis();

        return now.compareTo(expireDate) > 0;
    }
}
