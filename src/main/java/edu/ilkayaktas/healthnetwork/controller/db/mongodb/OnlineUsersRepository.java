package edu.ilkayaktas.healthnetwork.controller.db.mongodb;

import edu.ilkayaktas.healthnetwork.model.db.OnlineUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ilkayaktas on 26.03.2018 at 00:22.
 */

public interface OnlineUsersRepository extends MongoRepository<OnlineUser, String> {
    OnlineUser findByUserId(String userId);
    void deleteByUserId(String userId);
}
