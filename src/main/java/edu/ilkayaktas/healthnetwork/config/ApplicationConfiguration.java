package edu.ilkayaktas.healthnetwork.config;

import edu.ilkayaktas.healthnetwork.model.BarService;
import edu.ilkayaktas.healthnetwork.model.FooService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ilkayaktas on 24.03.2018 at 02:11.
 */

@Configuration
public class ApplicationConfiguration {
    @Bean
    public FooService fooService() {
        return new FooService();
    }

    @Bean(initMethod="doAnything")
    public BarService barService(){
        return new BarService();
    }
}
