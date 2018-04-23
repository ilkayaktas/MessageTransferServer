package edu.ilkayaktas.healthnetwork.rest.message.service;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.db.BloodSugarData;
import edu.ilkayaktas.healthnetwork.model.db.Channel;
import edu.ilkayaktas.healthnetwork.model.db.HealthData;
import edu.ilkayaktas.healthnetwork.model.db.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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
            dataManager.saveMessage(message);

            Channel ch = dataManager.getChannelById(message.toChannelId);
            checkChannelExistance(ch);

            dataManager.sendMessageToFCMGroup(message, ch.notificationKey);
        } else {
            throw new IllegalArgumentException("Message destination is undefined. toChannelId or toUserId should be set.");
        }
    }

    private void checkChannelExistance(Channel channel){
        try {
            String notificationKey = dataManager.getNotificationKeyOfGroup(channel.channelName);
            channel.notificationKey = notificationKey;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getMessages(String channelId){
        return dataManager.getMessagesByChannel(channelId);
    }

    public HealthData saveHealthData(HealthData healthData){
        return dataManager.saveHealthData(healthData);
    }

    public List<HealthData> getHealthData(String userId, HealthData.HealthDataType healthDataType){
        return dataManager.getHealthData(userId, healthDataType);
    }

    public BloodSugarData saveBloodSugar(BloodSugarData bloodSugarData){
        return dataManager.saveBloodSugar(bloodSugarData);
    }

    public List<BloodSugarData> getBloodSugar(String userId, BloodSugarData.SugarMeasurementType sugarMeasurementType){
        return dataManager.getBloodSugar(userId, sugarMeasurementType);
    }
}
