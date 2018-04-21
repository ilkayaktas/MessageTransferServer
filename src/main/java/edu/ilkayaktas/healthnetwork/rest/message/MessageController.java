package edu.ilkayaktas.healthnetwork.rest.message;

import edu.ilkayaktas.healthnetwork.model.db.Message;
import edu.ilkayaktas.healthnetwork.model.utils.AppConstants;
import edu.ilkayaktas.healthnetwork.rest.message.service.MessagePresenter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * Created by ilkayaktas on 18.04.2018 at 14:38.
 */

@RestController
public class MessageController {
    @Autowired
    Logger logger;

    @Autowired
    MessagePresenter messagePresenter;

    @RequestMapping(value = "/message/send", method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        try {
            logger.info("message/send"+" message:"+message);

            messagePresenter.distributeMessage(message);
            return new ResponseEntity<>(AppConstants.HTTP_STATUS_OK);

        } catch (IOException e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), AppConstants.HTTP_STATUS_BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/message/get", method = RequestMethod.GET)
    public ResponseEntity<List<Message>> getMessages(@RequestParam("channelId") String channelId) {
        logger.info("message/get"+" channelId:"+channelId);
        List<Message> messages = messagePresenter.getMessages(channelId);
        return new ResponseEntity<>(messages, AppConstants.HTTP_STATUS_OK);
    }

}
