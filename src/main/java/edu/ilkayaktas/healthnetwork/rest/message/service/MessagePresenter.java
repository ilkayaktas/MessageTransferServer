package edu.ilkayaktas.healthnetwork.rest.message.service;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.db.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ilkayaktas on 18.04.2018 at 14:38.
 */
@Service
public class MessagePresenter {
    private final IDataManager dataManager;

    @Autowired
    public MessagePresenter(IDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public void distributeMessage(Message message) throws IOException {
        if(message.toChannelId != null && !message.toChannelId.isEmpty()){
            dataManager.sendMessageToFCMGroupByUserId(message);
        } else{
            throw new IllegalArgumentException("Message destination is undefined. toChannelId or toUserId should be set.");
        }
    }
}
