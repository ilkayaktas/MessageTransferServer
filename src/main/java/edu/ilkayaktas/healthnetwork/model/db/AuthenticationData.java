package edu.ilkayaktas.healthnetwork.model.db;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by ilkayaktas on 26.03.2018 at 00:14.
 */

@Document(collection = "authenticationdata")
public class AuthenticationData {

    @Id
    public String id;

    public String userId;

    public String token;

    public String expireDate;

    public String date;

    public AuthenticationData(String userId, String token, String expireDateInMilis) {
        this.userId = userId;
        this.token = token;
        this.expireDate = expireDateInMilis;
        this.date = String.valueOf(System.currentTimeMillis());
    }
}
