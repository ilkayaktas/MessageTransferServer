package edu.ilkayaktas.healthnetwork.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FCMMessage {

    @SerializedName("to")
    @Expose
    public String to;
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("notification")
    @Expose
    public Notification notification;

    public FCMMessage() {
        data = new Data();
        notification = new Notification();
    }
}
