package edu.ilkayaktas.healthnetwork.controller;

import edu.ilkayaktas.healthnetwork.controller.api.IApiHelper;
import edu.ilkayaktas.healthnetwork.controller.db.IDbHelper;
import org.springframework.beans.factory.annotation.Autowired;

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
    public boolean saveUser() {

        return false;
    }

    @Override
    public boolean isUserOnline(String userId) {
        return dbHelper.isUserOnline(userId);
    }

    @Override
    public void setUserOnline(String userId) {
        dbHelper.setUserOnline(userId);
    }

    @Override
    public void setUserOffline(String userId) {
        dbHelper.setUserOffline(userId);
    }
}
