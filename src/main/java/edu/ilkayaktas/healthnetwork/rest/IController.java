package edu.ilkayaktas.healthnetwork.rest;

import edu.ilkayaktas.healthnetwork.model.rest.AuthorizationData;
import org.springframework.http.ResponseEntity;

/**
 * Created by aselsan on 27.03.2018 at 17:40.
 */

public interface IController {
    ResponseEntity<AuthorizationData> isAuthorized(String userId, String token);
}
