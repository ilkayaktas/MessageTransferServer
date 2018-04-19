package edu.ilkayaktas.healthnetwork.controller.api;

import com.google.gson.Gson;
import edu.ilkayaktas.healthnetwork.model.api.FCMChannel;
import edu.ilkayaktas.healthnetwork.model.api.FCMChannelResponse;
import edu.ilkayaktas.healthnetwork.model.api.FCMMessage;
import edu.ilkayaktas.healthnetwork.model.api.FCMMessageResponse;
import edu.ilkayaktas.healthnetwork.model.db.Message;
import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * Created by ilkayaktas on 25.03.2018 at 15:59.
 */

public class ApiHelper implements IApiHelper {

    @Autowired
    Logger logger;
    private String authKey = "AAAAqXV2Pr0:APA91bFNOinyYTG9AGoIWCennEPtmSmroK_be97FiUk6xdF0oIpo1VZ_LMBt2BFYHqD_XZWqpXTeUVdgRQTu_zpYSpF1Topt2fhSYPxBAa1-MlbEmRhMohP-h27RUcSb63DC1MCOymkR";

    @PostConstruct
    public void init() {
        logger.info("ApiHelper is constructed!");
    }

    @Override
    public String createFCMGroup(String groupName, String fcmToken) throws IOException {
        FCMChannel fcmChannel = new FCMChannel();
        fcmChannel.notificationKeyName = groupName;
        fcmChannel.operation = "create";
        fcmChannel.registrationIds.add(fcmToken);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "key="+authKey)
                            .addHeader("project_id", "727820156605")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                })
                .build();

        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(fcmChannel));

        Request request = new Request.Builder()
                .url("https://iid.googleapis.com/notification")
                .post(body)
                .build();

        return extractNotificationKey(okClient, request);


    }

    @Override
    public String addUserToFCMGroup(String groupName, String notificationKey, String fcmToken) throws IOException {
        FCMChannel fcmChannel = new FCMChannel();
        fcmChannel.notificationKeyName = groupName;
        fcmChannel.notificationKey = notificationKey;
        fcmChannel.operation = "add";
        fcmChannel.registrationIds.add(fcmToken);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "key="+authKey)
                            .addHeader("project_id", "727820156605")
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                })
                .build();

        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(fcmChannel));

        Request request = new Request.Builder()
                .url("https://iid.googleapis.com/notification")
                .post(body)
                .build();

        return extractNotificationKey(okClient, request);

    }

    @Override
    public void sendMessageToFCMGroup(Message message) throws IOException {
        FCMMessage msg = new FCMMessage();
        msg.to = message.toChannelId;
        msg.data.messageText = message.messageText;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "key="+authKey)
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                })
                .build();

        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(msg));

        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .post(body)
                .build();

        Response response = okClient.newCall(request).execute();
        FCMMessageResponse fcmChannelResponse = new Gson().fromJson(response.body().string(), FCMMessageResponse.class);

        if(response.code() == 200) {
            return ;
        }

        if(response.code() == 400){
            throw new IllegalArgumentException("Number of device that is failed to receive message: " + fcmChannelResponse.failure);
        }
    }

    @Override
    public void sendMessageToUser(Message message, String fcmToken) throws IOException {
        FCMMessage msg = new FCMMessage();
        msg.to = fcmToken;
        msg.data.messageText = message.messageText;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(chain -> {
                    Request original = chain.request();

                    Request request = original.newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .addHeader("Authorization", "key="+ authKey)
                            .method(original.method(), original.body())
                            .build();

                    return chain.proceed(request);
                })
                .build();

        MediaType JSON
                = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, new Gson().toJson(msg));

        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/fcm/send")
                .post(body)
                .build();

        Response response = okClient.newCall(request).execute();
        FCMMessageResponse fcmChannelResponse = new Gson().fromJson(response.body().string(), FCMMessageResponse.class);

        if(response.code() == 200) {
            return ;
        }

        if(response.code() == 400){
            throw new IllegalArgumentException("Number of device that is failed to receive message: " + fcmChannelResponse.failure);
        }
    }

    private String extractNotificationKey(OkHttpClient okClient, Request request) throws IOException {
        Response response = okClient.newCall(request).execute();
        FCMChannelResponse fcmChannelResponse = new Gson().fromJson(response.body().string(), FCMChannelResponse.class);

        if(response.code() == 200) {
                return fcmChannelResponse.notificationKey;
        }

        if(response.code() == 400){
            throw new IllegalArgumentException(fcmChannelResponse.error);
        }

        return null;
    }

}
