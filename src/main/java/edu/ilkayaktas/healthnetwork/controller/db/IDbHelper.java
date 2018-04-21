package edu.ilkayaktas.healthnetwork.controller.db;

import edu.ilkayaktas.healthnetwork.model.db.*;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * Created by ilkayaktas on 25.03.2018 at 15:59.
 */

public interface IDbHelper {
    // authentication data
    AuthenticationData saveAuthenticationData(@NonNull AuthenticationData authenticationData);
    AuthenticationData updateAuthenticationData(@NonNull AuthenticationData authenticationData);
    AuthenticationData getAuthenticationData(@NonNull String userId);
    void deleteAuthenticationData(@NonNull String userId);
    boolean isAuthenticationDataExists(@NonNull String userId);

    // user
    User saveUser(@NonNull User user);
    boolean updateUserField(@NonNull String userId, @NonNull String field, @NonNull String value);
    User getUser(@NonNull String userId);
    User getUserByEmail(@NonNull String email);
    boolean isUserExist(@NonNull String userId);

    // online status
    boolean isUserOnline(@NonNull String userId);
    OnlineUser setUserOnline(@NonNull String userId);
    void setUserOffline(@NonNull String userId);

    // channel
    Channel saveChannel(@NonNull Channel channel);
    Channel getChannelById(@NonNull String channelId);
    List<Channel> getUserChannels(@NonNull String userId);
    List<Channel> getUserChannelsByGuestUserId(@NonNull String guestUserId);

    // message
    Message saveMessage(@NonNull Message message);
    List<Message> getMessagesByChannel(@NonNull String channelId);
}
