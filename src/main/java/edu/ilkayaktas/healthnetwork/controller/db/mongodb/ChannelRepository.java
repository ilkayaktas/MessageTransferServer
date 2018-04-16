package edu.ilkayaktas.healthnetwork.controller.db.mongodb;

import edu.ilkayaktas.healthnetwork.model.db.Channel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ilkayaktas on 16.04.2018 at 00:42.
 */

public interface ChannelRepository extends MongoRepository<Channel, String> {
    List<Channel> findChannelByOwner(String userId);
}
