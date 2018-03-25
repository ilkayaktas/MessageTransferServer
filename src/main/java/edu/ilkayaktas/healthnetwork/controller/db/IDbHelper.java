package edu.ilkayaktas.healthnetwork.controller.db;

/**
 * Created by ilkayaktas on 25.03.2018 at 15:59.
 */

public interface IDbHelper {
    // user
    boolean saveUser();

    // online status
    boolean isUserOnline(String userId);
    void setUserOnline(String userId);
    void setUserOffline(String userId);
}
