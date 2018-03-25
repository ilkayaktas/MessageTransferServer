package edu.ilkayaktas.healthnetwork.controller.db;

import javax.annotation.PostConstruct;

/**
 * Created by ilkayaktas on 25.03.2018 at 16:00.
 */

public class DbHelper implements IDbHelper {

    @PostConstruct
    public void init() {
        System.out.println("DbHelper is constructed!");
    }

}
