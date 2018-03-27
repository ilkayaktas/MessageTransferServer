package edu.ilkayaktas.healthnetwork.rest;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.rest.AuthorizationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

/**
 * Created by aselsan on 27.03.2018 at 13:51.
 */

public class AuthorizationController {

    @Autowired
    private IDataManager dataManager;

    public ResponseEntity<AuthorizationData> isRequestAuthorized(String userId, String token){
        return dataManager.isRequestAuthorized(userId, token);
    }
}
