package edu.ilkayaktas.healthnetwork.controller.db;

import com.mongodb.client.result.UpdateResult;
import edu.ilkayaktas.healthnetwork.controller.db.mongodb.AuthenticationRepository;
import edu.ilkayaktas.healthnetwork.controller.db.mongodb.ChannelRepository;
import edu.ilkayaktas.healthnetwork.controller.db.mongodb.OnlineUsersRepository;
import edu.ilkayaktas.healthnetwork.controller.db.mongodb.UserRepository;
import edu.ilkayaktas.healthnetwork.model.db.AuthenticationData;
import edu.ilkayaktas.healthnetwork.model.db.Channel;
import edu.ilkayaktas.healthnetwork.model.db.OnlineUser;
import edu.ilkayaktas.healthnetwork.model.db.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.lang.NonNull;

import javax.annotation.PostConstruct;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by ilkayaktas on 25.03.2018 at 16:00.
 */

public class DbHelper implements IDbHelper {
    // TODO Update metotları için performans incelenecek.


    @Autowired
    OnlineUsersRepository onlineUsersRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationRepository authenticationRepository;

    @Autowired
    ChannelRepository channelRepository;

    @Autowired
    MongoTemplate mongoTemplate; // for updating data

    @PostConstruct
    public void init() {
        System.out.println("DbHelper is constructed!");
    }

    @Override
    public AuthenticationData saveAuthenticationData(@NonNull AuthenticationData authenticationData) {
        return authenticationRepository.save(authenticationData);
    }

    @Override
    public AuthenticationData updateAuthenticationData(@NonNull AuthenticationData authenticationData) {

        Query query = new Query(where("userId").is(authenticationData.userId));

        mongoTemplate.updateFirst(query, Update.update("token", authenticationData.token), AuthenticationData.class);
        mongoTemplate.updateFirst(query, Update.update("expireDate", authenticationData.expireDate), AuthenticationData.class);
        mongoTemplate.updateFirst(query, Update.update("date", authenticationData.date), AuthenticationData.class);

        return authenticationRepository.findByUserId(authenticationData.userId);
    }

    @Override
    public AuthenticationData getAuthenticationData(@NonNull String userId) {
        return authenticationRepository.findByUserId(userId);
    }

    @Override
    public void deleteAuthenticationData(@NonNull String userId) {
        authenticationRepository.deleteByUserId(userId);
    }

    @Override
    public boolean isAuthenticationDataExists(@NonNull String userId){
        return authenticationRepository.existsByUserId(userId);
    }

    @Override
    public User saveUser(@NonNull User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public boolean updateUserField(@NonNull String userId, @NonNull String field, @NonNull String value){
        Query query = new Query(where("userId").is(userId));

        UpdateResult result = mongoTemplate.updateFirst(query, Update.update(field, value), AuthenticationData.class);

        return result.wasAcknowledged();
    }

    @Override
    public User getUser(@NonNull String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public boolean isUserExist(@NonNull String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public boolean isUserOnline(@NonNull String userId) {
        OnlineUser onlineUser = onlineUsersRepository.findByUserId(userId);
        return onlineUser != null;
    }

    @Override
    public OnlineUser setUserOnline(@NonNull String userId) {
        onlineUsersRepository.deleteByUserId(userId);
        return onlineUsersRepository.save(new OnlineUser(userId));
    }

    @Override
    public void setUserOffline(@NonNull String userId) {
        onlineUsersRepository.deleteByUserId(userId);
    }

    @Override
    public Channel saveChannel(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public List<Channel> getUserChannels(String userId) {
        return channelRepository.findChannelByOwner(userId);
    }

    @Override
    public List<Channel> getUserChannelsByToken(String fcmToken) {
        Query query = new Query(where("membersFCMTokens").in(fcmToken));
        return mongoTemplate.find(query, Channel.class);
    }

}
