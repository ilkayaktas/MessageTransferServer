package edu.ilkayaktas.healthnetwork.rest.user.service;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.db.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by ilkayaktas on 16.04.2018 at 00:28.
 */

@Service
public class ChannelPresenter {
    private final IDataManager dataManager;

    @Autowired
    public ChannelPresenter(IDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public Channel createChannel(Channel channel) throws IOException {
        channel.notificationKey = dataManager.createFCMGroup(channel.channelName, channel.membersFCMTokens.get(0));
        return dataManager.saveChannel(channel);
    }
}
