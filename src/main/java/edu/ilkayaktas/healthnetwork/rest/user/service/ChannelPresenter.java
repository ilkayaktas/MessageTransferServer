package edu.ilkayaktas.healthnetwork.rest.user.service;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.db.Channel;
import edu.ilkayaktas.healthnetwork.model.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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

    public Channel updateChannel(String channelId, String email) throws IOException {
        User user = dataManager.getUserByEmail(email);
        Channel channel = dataManager.getChannelById(channelId);

        if(user != null && channel != null){
            String notificationKey = dataManager.addUserToFCMGroup(channel.channelName, channel.notificationKey, user.fcmToken);
            return null;
        }else{
            throw new IllegalArgumentException("User email or channel id is incorrect!");
        }
    }

    public List<Channel> getUserChannel(String userId){
        return dataManager.getUserChannels(userId);
    }

    public List<Channel> getUserChannelByToken(String token){
        return dataManager.getUserChannelsByToken(token);
    }
}
