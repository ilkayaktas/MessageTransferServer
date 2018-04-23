package edu.ilkayaktas.healthnetwork.controller.api;

import edu.ilkayaktas.healthnetwork.model.db.Message;

import java.io.IOException;
import java.util.List;

/**
 * Created by ilkayaktas on 25.03.2018 at 15:58.
 */

public interface IApiHelper {
    String createFCMGroup(String groupName, String fcmToken) throws IOException;

    String addUserToFCMGroup(String groupName, String notificationKey, List<String> fcmToken) throws IOException;

    void sendMessageToFCMGroup(Message message, String notificationKey) throws IOException;

    String getNotificationKeyOfGroup(String groupName) throws IOException;
}
