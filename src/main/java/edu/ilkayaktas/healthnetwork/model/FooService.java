package edu.ilkayaktas.healthnetwork.model;

import javax.annotation.PostConstruct;

/**
 * Created by ilkayaktas on 24.03.2018 at 02:09.
 */

public class FooService {
    @PostConstruct
    public void init() {
        System.out.println("FooService is constructed!");
    }
}
