package edu.ilkayaktas.healthnetwork.controller;

import edu.ilkayaktas.healthnetwork.controller.api.IApiHelper;
import edu.ilkayaktas.healthnetwork.controller.db.IDbHelper;
import edu.ilkayaktas.healthnetwork.model.db.AuthenticationData;
import edu.ilkayaktas.healthnetwork.model.db.Channel;
import edu.ilkayaktas.healthnetwork.model.db.OnlineUser;
import edu.ilkayaktas.healthnetwork.model.db.User;
import edu.ilkayaktas.healthnetwork.model.rest.AuthorizationData;
import edu.ilkayaktas.healthnetwork.model.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Created by ilkayaktas on 25.03.2018 at 16:02.
 */

public class DataManager implements IDataManager{

    // TODO upsertUserField performans olarak değerlendirilecek

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
    public boolean isAuthenticationDataExists(@NonNull String userId){
        return dbHelper.isAuthenticationDataExists(userId);
    }

    @Override
    public AuthenticationData upsertAuthenticationData(AuthenticationData authenticationData){
        if(isAuthenticationDataExists(authenticationData.userId)){
            updateAuthenticationData(authenticationData);
            return getAuthenticationData(authenticationData.userId);
        } else{
            return saveAuthenticationData(authenticationData);
        }
    }

    @Override
    public User saveUser(User user) {
        return dbHelper.saveUser(user);
    }

    @Override
    public boolean updateUserField(@NonNull String userId, @NonNull String field, @NonNull String value){
        return dbHelper.updateUserField(userId, field, value);
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
    public OnlineUser setUserOnline(String userId) {
        return dbHelper.setUserOnline(userId);
    }

    @Override
    public void setUserOffline(String userId) {
        dbHelper.setUserOffline(userId);
    }

    @Override
    public Channel saveChannel(Channel channel) {
        return dbHelper.saveChannel(channel);
    }

    @Override
    public List<Channel> getUserChannels(String userId) {
        return dbHelper.getUserChannels(userId);
    }

    @Override
    public List<Channel> getUserChannelsByToken(String fcmToken) {
        return dbHelper.getUserChannelsByToken(fcmToken);
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

    @Override
    public User upsertUser(User user) {
        if( dbHelper.isUserExist(user.userId) ){
            updateUserField(user.userId, "email", user.email);
            updateUserField(user.userId, "name", user.name);
            updateUserField(user.userId, "picture", user.picture);
            updateUserField(user.userId, "fcmToken", user.fcmToken);
            updateUserField(user.userId, "userId", user.userId);
            return getUser(user.userId);
        }else{
            return saveUser(user);
        }
    }

    private boolean isExpired(Long expireDate){
        Long now = System.currentTimeMillis();

        return now.compareTo(expireDate) > 0;
    }

    @Override
    public String createFCMGroup(String groupName, String fcmToken) throws IOException {
        return apiHelper.createFCMGroup(groupName,fcmToken);
    }

    @Override
    public String addUserToFCMGroup(String groupName, String notificationKey, String fcmToken) throws IOException {
        return apiHelper.addUserToFCMGroup(groupName,notificationKey,fcmToken);
    }
}
