package edu.ilkayaktas.healthnetwork.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ilkayaktas on 26.03.2018 at 00:09.
 */

@Document(collection = "user")
public class User {
    @Id
    public String id;

    public String userId;

    public String picture;

    public String name;

    public String email;

    public String fcmToken;
}
