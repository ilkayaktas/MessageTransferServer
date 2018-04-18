package edu.ilkayaktas.healthnetwork.rest.user.service;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.db.Channel;
import edu.ilkayaktas.healthnetwork.model.db.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    Logger logger;

    public Channel createChannel(Channel channel) throws IOException {
        User user = dataManager.getUser(channel.guestUserIds.get(0));
        channel.notificationKey = dataManager.createFCMGroup(channel.channelName, user.fcmToken);
        return dataManager.saveChannel(channel);
    }

    public Channel updateChannel(String channelId, String email) throws IOException {
        User user = dataManager.getUserByEmail(email);
        Channel channel = dataManager.getChannelById(channelId);

        if(user != null && channel != null){
            dataManager.addUserToFCMGroup(channel.channelName, channel.notificationKey, user.fcmToken);
            channel.guestUserIds.add(user.userId);

            dataManager.saveChannel(channel);
            return channel;
        }else{
            throw new IllegalArgumentException("User email or channel id is incorrect!");
        }
    }

    public List<Channel> getUserChannel(String userId){
        Map<String, Channel> userChannels = new HashMap<>();

        List<Channel> list = dataManager.getUserChannels(userId);
        list.forEach(channel -> {
            userChannels.put(channel.id, channel);
            logger.debug("Channels by userId: " + channel.channelName);
        });

        list = getUserChannelsByGuestUserId(userId);
        list.forEach(channel -> {
            userChannels.put(channel.id, channel);
            logger.debug("Channels by guest userId: " + channel.channelName);
        });

        return new ArrayList<>(userChannels.values());
    }

    private List<Channel> getUserChannelsByGuestUserId(String guestUserId){
        return dataManager.getUserChannelsByGuestUserId(guestUserId);
    }

    public Channel getChannel(String channelId){
        return dataManager.getChannelById(channelId);
    }

}
