package edu.ilkayaktas.healthnetwork.controller.api;

import javax.annotation.PostConstruct;

/**
 * Created by ilkayaktas on 25.03.2018 at 15:59.
 */

public class ApiHelper implements IApiHelper {

    @PostConstruct
    public void init() {
        System.out.println("ApiHelper is constructed!");
    }

}
