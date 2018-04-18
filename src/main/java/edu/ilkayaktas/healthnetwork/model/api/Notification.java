package edu.ilkayaktas.healthnetwork.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {

    @SerializedName("body")
    @Expose
    public String body;
    @SerializedName("title")
    @Expose
    public String title;

}
