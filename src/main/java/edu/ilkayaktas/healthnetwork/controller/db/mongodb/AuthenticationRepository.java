package edu.ilkayaktas.healthnetwork.controller.db.mongodb;

import edu.ilkayaktas.healthnetwork.model.db.AuthenticationData;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by aselsan on 27.03.2018 at 16:52.
 */

public interface AuthenticationRepository extends MongoRepository<AuthenticationData, String> {
    AuthenticationData findByuserId(String userId);
    boolean existsByuserId(String userId);
    void deleteByuserId(String userId);
}
