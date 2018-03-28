package edu.ilkayaktas.healthnetwork.rest.user;

import edu.ilkayaktas.healthnetwork.model.db.User;
import edu.ilkayaktas.healthnetwork.rest.IController;
import org.springframework.http.ResponseEntity;

/**
 * Created by aselsan on 27.03.2018 at 16:25.
 */

public interface IUserController extends IController {
    ResponseEntity<?> upsertUser(User user, String userId, String token);

}
