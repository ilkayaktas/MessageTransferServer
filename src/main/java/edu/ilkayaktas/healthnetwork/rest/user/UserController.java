package edu.ilkayaktas.healthnetwork.rest.user;

import edu.ilkayaktas.healthnetwork.model.db.Channel;
import edu.ilkayaktas.healthnetwork.model.db.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by aselsan on 27.03.2018 at 18:02.
 */

@RestController
public class UserController {

    @RequestMapping(value = "user/get")
    public ResponseEntity<User> getUser(@RequestParam("userId") String userId){

        return null;
    }

    @RequestMapping(value = "user/channel")
    public ResponseEntity<Channel> getUserChannels(@RequestParam("userId") String userId){

        return null;
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public ResponseEntity<?> addUser(@RequestBody User user) {

        return null;
    }

    @RequestMapping(value = "channel/add", method = RequestMethod.POST)
    public ResponseEntity<?> addChannel(@RequestBody Channel channel) {

        return null;
    }

    @RequestMapping(value = "channel/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateChannel(@RequestParam("id") String channelId, @RequestParam("memberId") String memberId) {

        return null;
    }

}
