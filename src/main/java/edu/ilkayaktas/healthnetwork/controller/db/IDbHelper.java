package edu.ilkayaktas.healthnetwork.controller.db;

import edu.ilkayaktas.healthnetwork.model.db.AuthenticationData;
import edu.ilkayaktas.healthnetwork.model.db.OnlineUsers;
import edu.ilkayaktas.healthnetwork.model.db.User;

/**
 * Created by ilkayaktas on 25.03.2018 at 15:59.
 */

public interface IDbHelper {
    // authentication data
    AuthenticationData saveAuthenticationData(AuthenticationData authenticationData);
    AuthenticationData getAuthenticationData(String userId);
    void deleteAuthenticationData(String userId);

    // user
    User saveUser(User user);
    User getUser(String userId);
    boolean isUserExist(String userId);

    // online status
    boolean isUserOnline(String userId);
    OnlineUsers setUserOnline(String userId);
    void setUserOffline(String userId);
}
