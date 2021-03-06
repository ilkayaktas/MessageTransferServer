package edu.ilkayaktas.healthnetwork.rest.user.service;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ilkayaktas on 17.04.2018 at 00:37.
 */

@Service
public class UserPresenter {
    private final IDataManager dataManager;

    @Autowired
    public UserPresenter(IDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public User saveOrUpdate(User user){
        return dataManager.upsertUser(user);
    }

    public User getUserByEmail(String email){
        return dataManager.getUserByEmail(email);
    }

    public User getUser(String userId){
        return dataManager.getUser(userId);
    }
}
