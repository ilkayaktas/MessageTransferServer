package edu.ilkayaktas.healthnetwork.controller.api;

import edu.ilkayaktas.healthnetwork.model.db.Message;

import java.io.IOException;

/**
 * Created by ilkayaktas on 25.03.2018 at 15:58.
 */

public interface IApiHelper {
    String createFCMGroup(String groupName, String fcmToken) throws IOException;

    String addUserToFCMGroup(String groupName, String notificationKey, String fcmToken) throws IOException;

    void sendMessageToFCMGroup(Message message) throws IOException;

    void sendMessageToUser(Message message) throws IOException;
}
