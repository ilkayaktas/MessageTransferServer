package edu.ilkayaktas.healthnetwork.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aselsan on 18.04.2018 at 17:55.
 */

public class FCMMessageResponse {

    @SerializedName("success")
    @Expose
    public int success;
    @SerializedName("failure")
    @Expose
    public int failure;
    @SerializedName("failed_registration_ids")
    @Expose
    public List<String> failedRegistrationIds = null;

}
