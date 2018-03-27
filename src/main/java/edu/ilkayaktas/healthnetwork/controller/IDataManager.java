package edu.ilkayaktas.healthnetwork.controller;

import edu.ilkayaktas.healthnetwork.controller.api.IApiHelper;
import edu.ilkayaktas.healthnetwork.controller.db.IDbHelper;
import edu.ilkayaktas.healthnetwork.model.rest.AuthorizationData;
import org.springframework.http.ResponseEntity;

/**
 * Created by ilkayaktas on 25.03.2018 at 16:01.
 */

public interface IDataManager extends IDbHelper, IApiHelper {
    ResponseEntity<AuthorizationData> isRequestAuthorized(String userId, String token);
}
