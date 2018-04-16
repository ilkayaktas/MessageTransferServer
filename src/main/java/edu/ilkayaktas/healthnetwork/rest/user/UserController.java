package edu.ilkayaktas.healthnetwork.rest.user;

import edu.ilkayaktas.healthnetwork.model.db.Channel;
import edu.ilkayaktas.healthnetwork.model.db.User;
import edu.ilkayaktas.healthnetwork.model.utils.AppConstants;
import edu.ilkayaktas.healthnetwork.rest.user.service.ChannelPresenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aselsan on 27.03.2018 at 18:02.
 */

@RestController
public class UserController {

    @Autowired
    ChannelPresenter channelPresenter;


    @RequestMapping(value = "user/get")
    public ResponseEntity<User> getUser(@RequestParam("userId") String userId){

        return null;
    }

    @RequestMapping(value = "user/channel")
    public ResponseEntity<?> getUserChannels(@RequestParam("userId") String userId, @RequestParam("fcmToken") String token){
        List<Channel> userChannels = new ArrayList<>();

        if(userId != null && !userId.isEmpty()){
            userChannels.addAll(channelPresenter.getUserChannel(userId));
            System.out.println("found channels by user id");
        }

        if(token != null && !token.isEmpty()){
            userChannels.addAll(channelPresenter.getUserChannelByToken(token));
            System.out.println("found channels by token");
        }
        return new ResponseEntity<>(userChannels, AppConstants.HTTP_STATUS_OK);
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user) {

        return null;
    }

    @RequestMapping(value = "channel/create", method = RequestMethod.POST)
    public ResponseEntity<?> addChannel(@RequestBody Channel channel) {
        try {
            Channel newChannel = channelPresenter.createChannel(channel);
            return new ResponseEntity<>(newChannel, AppConstants.HTTP_STATUS_OK);
        } catch (IOException | IllegalArgumentException e) {
            // TODO loglama eklenmeli
            e.printStackTrace();
            return new ResponseEntity<>(e.getLocalizedMessage(), AppConstants.HTTP_STATUS_BAD_REQUEST);
        }

    }

    @RequestMapping(value = "channel/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateChannel(@RequestParam("id") String channelId, @RequestParam("memberId") String memberId) {

        return null;
    }

}
