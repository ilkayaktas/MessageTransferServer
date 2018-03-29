package edu.ilkayaktas.healthnetwork.controller.db.mongodb;


import edu.ilkayaktas.healthnetwork.model.db.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by aselsan on 27.03.2018 at 13:58.
 */

public interface UserRepository extends MongoRepository<User, String> {
    User findByuserId(String userId);
    boolean existsByuserId(String userId);
    void deleteByuserId(String userId);
}
