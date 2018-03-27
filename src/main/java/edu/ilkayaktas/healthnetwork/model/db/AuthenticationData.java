package edu.ilkayaktas.healthnetwork.model.db;

import org.springframework.data.annotation.Id;

/**
 * Created by ilkayaktas on 26.03.2018 at 00:14.
 */

public class AuthenticationData {

    @Id
    public String id;

    public String userId;

    public String token;

    public String expireDate;

    public String date;
}
