package edu.ilkayaktas.healthnetwork.controller.db.mongodb;

import edu.ilkayaktas.healthnetwork.model.db.Channel;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by ilkayaktas on 16.04.2018 at 00:42.
 */

public interface ChannelRepository extends MongoRepository<Channel, String> {
}
