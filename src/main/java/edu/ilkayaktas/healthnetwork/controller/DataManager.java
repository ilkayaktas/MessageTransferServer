package edu.ilkayaktas.healthnetwork.controller;

import edu.ilkayaktas.healthnetwork.controller.api.IApiHelper;
import edu.ilkayaktas.healthnetwork.controller.db.IDbHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by ilkayaktas on 25.03.2018 at 16:02.
 */

public class DataManager implements IDataManager{

    @Autowired
    private IDbHelper mIDbHelper;

    @Autowired
    private IApiHelper mIApiHelper;

    @PostConstruct
    public void init() {
        System.out.println("DataManager is constructed!");
    }
}
