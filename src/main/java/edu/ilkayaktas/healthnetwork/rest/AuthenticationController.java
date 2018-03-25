package edu.ilkayaktas.healthnetwork.rest;

import edu.ilkayaktas.healthnetwork.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ilkayaktas on 26.03.2018 at 00:07.
 */

@RestController
public class AuthenticationController {

    @Value("${application.message}")
    String message;

    @Value("${application.appname}")
    String appname;

    @Autowired
    LoginService loginService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    void login(@RequestParam("userId") String userId, @RequestParam("token") String token, @RequestParam("expiredate") String expireDateInMilis){
        loginService.login(userId, token, expireDateInMilis);
    }
}
