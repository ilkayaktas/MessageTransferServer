package edu.ilkayaktas.healthnetwork.model;

import org.springframework.data.annotation.Id;

/**
 * Created by ilkayaktas on 26.03.2018 at 00:09.
 */

public class User {
    @Id
    public String id;

    public String name;

    public String token;

    public String email;
}
