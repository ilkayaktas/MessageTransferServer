package edu.ilkayaktas.healthnetwork.rest.message;

import edu.ilkayaktas.healthnetwork.model.db.Message;
import edu.ilkayaktas.healthnetwork.model.utils.AppConstants;
import edu.ilkayaktas.healthnetwork.rest.message.service.MessagePresenter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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

}
