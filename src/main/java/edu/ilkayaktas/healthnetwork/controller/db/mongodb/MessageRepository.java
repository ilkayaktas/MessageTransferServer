package edu.ilkayaktas.healthnetwork.controller.db.mongodb;

import edu.ilkayaktas.healthnetwork.model.db.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ilkayaktas on 21.04.2018 at 15:10.
 */

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> getMessageByToChannelId(String channelId);
}
