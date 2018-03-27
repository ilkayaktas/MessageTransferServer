package edu.ilkayaktas.healthnetwork.rest.authentication;

import edu.ilkayaktas.healthnetwork.rest.IController;
import org.springframework.http.ResponseEntity;

/**
 * Created by aselsan on 27.03.2018 at 16:22.
 */

public interface IAuthenticationController extends IController {
    ResponseEntity<String> login(String userId, String token, String expireDateInMilis);
    ResponseEntity<?> logout(String userId, String token);
}