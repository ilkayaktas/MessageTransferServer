package edu.ilkayaktas.healthnetwork.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ilkayaktas on 26.03.2018 at 00:10.
 */

@Document(collection = "onlineuser")
public class OnlineUser {
    @Id
    public String id;

    @Indexed(unique = true)
    public String userId;

    public String timeInMilis;

    public OnlineUser(String userId) {
        this.userId = userId;
        this.timeInMilis = String.valueOf(System.currentTimeMillis());
    }
}
