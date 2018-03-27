package edu.ilkayaktas.healthnetwork.rest.user;

import edu.ilkayaktas.healthnetwork.model.db.User;
import edu.ilkayaktas.healthnetwork.model.rest.AuthorizationData;
import edu.ilkayaktas.healthnetwork.rest.AuthorizationController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by aselsan on 27.03.2018 at 18:02.
 */

public class UserController implements IUserController {

    @Autowired
    AuthorizationController authorizationController;


    @RequestMapping(value = "/upsert/user", method = RequestMethod.POST)
    @Override
    public ResponseEntity<?> upsertUser(@RequestBody User user, @RequestParam("userId") String userId, @RequestParam("token") String token) {
        // check if the request is authorized
        if(isAuthorized(userId, token).getStatusCode().isError()){
            // not authorized or token is expired
            return isAuthorized(userId, token);
        }

        return null;
    }

    @Override
    public ResponseEntity<AuthorizationData> isAuthorized(String userId, String token) {
        return authorizationController.isRequestAuthorized(userId, token);
    }
}
