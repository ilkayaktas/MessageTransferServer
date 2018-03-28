package edu.ilkayaktas.healthnetwork.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by ilkayaktas on 29.03.2018 at 01:02.
 */

@Document(collection = "channel")
public class Channel {
    @Id
    public String id; // channel id

    public String channelName;

    public String createdBy; // userId

    public String connectionEndpoint;

    public List<String> members;

}
