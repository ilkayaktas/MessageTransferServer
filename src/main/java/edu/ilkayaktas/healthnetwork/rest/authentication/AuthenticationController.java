package edu.ilkayaktas.healthnetwork.rest.authentication;

import edu.ilkayaktas.healthnetwork.model.db.User;
import edu.ilkayaktas.healthnetwork.rest.authentication.service.LoginServicePresenter;
import edu.ilkayaktas.healthnetwork.model.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ilkayaktas on 26.03.2018 at 00:07.
 */

@RestController
public class AuthenticationController {

    @Autowired
    LoginServicePresenter loginServicePresenter;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody User user,
                                        @RequestParam(value = "userId") String userId,
                                        @RequestParam(value="token") String token,
                                        @RequestParam(value="expiredate") String expireDate){

        loginServicePresenter.login(user, userId, token, expireDate);
        return new ResponseEntity<>("Login succesful!", AppConstants.HTTP_STATUS_OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> logout(@RequestParam("userId") String userId,
                                    @RequestParam("token") String token){
        
        loginServicePresenter.logout(userId);
        return new ResponseEntity<>("logout succesful!", AppConstants.HTTP_STATUS_OK);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity handleMissingParams(MissingServletRequestParameterException ex) {
        System.out.println(ex.getMessage());
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
