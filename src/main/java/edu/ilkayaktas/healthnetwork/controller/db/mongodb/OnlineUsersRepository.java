package edu.ilkayaktas.healthnetwork.controller.db.mongodb;

import edu.ilkayaktas.healthnetwork.model.db.OnlineUsers;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ilkayaktas on 26.03.2018 at 00:22.
 */

public interface OnlineUsersRepository extends MongoRepository<OnlineUsers, String> {
    OnlineUsers findByUserId(String userId);

    void deleteByUserId(String userId);
}
