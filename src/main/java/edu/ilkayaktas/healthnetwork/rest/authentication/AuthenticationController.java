package edu.ilkayaktas.healthnetwork.rest.authentication;

import edu.ilkayaktas.healthnetwork.model.AppConstants;
import edu.ilkayaktas.healthnetwork.model.rest.AuthorizationData;
import edu.ilkayaktas.healthnetwork.rest.AuthorizationController;
import edu.ilkayaktas.healthnetwork.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ilkayaktas on 26.03.2018 at 00:07.
 */

@RestController
public class AuthenticationController implements IAuthenticationController{

    @Autowired
    AuthorizationController authorizationController;

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam("userId") String userId, @RequestParam("token") String token, @RequestParam("expiredate") String expireDateInMilis){
        loginService.login(userId, token, expireDateInMilis);
        return new ResponseEntity<>("Login succesful!",AppConstants.HTTP_STATUS_OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(@RequestParam("userId") String userId, @RequestParam("token") String token){
        // check if the request is authorized
        if(isAuthorized(userId, token).getStatusCode().isError()){
            // not authorized or token is expired
            return isAuthorized(userId, token);
        }

        loginService.logout(userId);
        return new ResponseEntity<>("logout succesful!",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AuthorizationData> isAuthorized(String userId, String token) {
        return authorizationController.isRequestAuthorized(userId, token);
    }
}
