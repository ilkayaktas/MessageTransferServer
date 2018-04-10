package edu.ilkayaktas.healthnetwork.di;

import edu.ilkayaktas.healthnetwork.controller.DataManager;
import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.controller.api.ApiHelper;
import edu.ilkayaktas.healthnetwork.controller.api.IApiHelper;
import edu.ilkayaktas.healthnetwork.controller.db.DbHelper;
import edu.ilkayaktas.healthnetwork.controller.db.IDbHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ilkayaktas on 25.03.2018 at 15:57.
 */
@Configuration
public class DataManagerInjector {

    @Bean
    public IDataManager provideDataManager(){
        return new DataManager();
    }

    @Bean
    public IDbHelper provideDbHelper(){
        return new DbHelper();
    }

    @Bean
    public IApiHelper provideApiHelper(){
        return new ApiHelper();
    }

    @Bean
    public AuthorizationController provideAuthorizationController(){
        return new AuthorizationController();
    }
}
