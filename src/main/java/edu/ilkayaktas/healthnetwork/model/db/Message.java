package edu.ilkayaktas.healthnetwork.model.db;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by aselsan on 18.04.2018 at 11:08.
 */

public class Message {
    @Id
    public String id; // message id

    public String senderUserId;

    public String messageText;

    public String toChannelId;

    public String toUserId;

    public Date createdAt;
}
