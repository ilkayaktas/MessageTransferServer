package edu.ilkayaktas.healthnetwork.rest;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ilkayaktas on 29.03.2018 at 23:03.
 */
@Configuration
public class InitializeController {

    @Autowired
    private IDataManager dataManager;

    @Autowired
    private void initChannels(){
        System.out.println("Public channels are added!");
        dataManager.upsertAuthenticationData(null);
    }
}
