package edu.ilkayaktas.healthnetwork.rest.user;

import edu.ilkayaktas.healthnetwork.model.db.Channel;
import edu.ilkayaktas.healthnetwork.model.db.User;
import edu.ilkayaktas.healthnetwork.model.utils.AppConstants;
import edu.ilkayaktas.healthnetwork.rest.user.service.ChannelPresenter;
import edu.ilkayaktas.healthnetwork.rest.user.service.UserPresenter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by ilkayaktas on 27.03.2018 at 18:02.
 */

@RestController
public class UserController {

    @Autowired
    ChannelPresenter channelPresenter;

    @Autowired
    UserPresenter userPresenter;

    @Autowired
    Logger logger;

    @RequestMapping(value = "user/get")
    public ResponseEntity<User> getUser(@RequestParam("userId") String userId){
        logger.info("user/get"+" userId:"+userId);

        User user = userPresenter.getUser(userId);
        return new ResponseEntity<>(user, AppConstants.HTTP_STATUS_OK);
    }

    @RequestMapping(value = "user/channel")
    public ResponseEntity<?> getUserChannels(@RequestParam("userId") String userId){
        logger.info("user/channel"+" userId:"+userId);

        if(userId != null && !userId.isEmpty()){
            List<Channel> list = channelPresenter.getUserChannel(userId);
            return new ResponseEntity<>(list, AppConstants.HTTP_STATUS_OK);
        }

        return new ResponseEntity<>("userId is empty!", AppConstants.HTTP_STATUS_BAD_REQUEST);
    }

    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        logger.info("/user/save"+" user:"+user);

        User usr = userPresenter.saveOrUpdate(user);
        return new ResponseEntity<>(usr, AppConstants.HTTP_STATUS_OK);
    }

    @RequestMapping(value = "channel/create", method = RequestMethod.POST)
    public ResponseEntity<?> addChannel(@RequestBody Channel channel) {
        try {
            logger.info("channel/create"+" channel:"+channel);

            Channel newChannel = channelPresenter.createChannel(channel);
            return new ResponseEntity<>(newChannel, AppConstants.HTTP_STATUS_OK);
        } catch (IOException | IllegalArgumentException e) {
            // TODO loglama eklenmeli
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), AppConstants.HTTP_STATUS_BAD_REQUEST);
        }

    }

    @RequestMapping(value = "channel/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateChannel(@RequestParam("id") String channelId, @RequestParam("email") String email) {
        try {
            logger.info("channel/update"+" id:"+channelId+" email:"+email);

            Channel channel = channelPresenter.updateChannel(channelId, email);
            return new ResponseEntity<>(channel, AppConstants.HTTP_STATUS_OK);
        } catch (IOException | IllegalArgumentException e) {
            // TODO loglama eklenmeli
            System.err.println(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), AppConstants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "channel/get")
    public ResponseEntity<Channel> getChannel(@RequestParam("id") String channelId){
        logger.info("channel/get"+" id:"+channelId);

        Channel channel = channelPresenter.getChannel(channelId);
        return new ResponseEntity<>(channel, AppConstants.HTTP_STATUS_OK);
    }

}
