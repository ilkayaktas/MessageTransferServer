package edu.ilkayaktas.healthnetwork.controller.services;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.db.BloodSugarData;
import edu.ilkayaktas.healthnetwork.model.db.Channel;
import edu.ilkayaktas.healthnetwork.model.db.Message;
import edu.ilkayaktas.healthnetwork.rest.message.service.MessagePresenter;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by aselsan on 7.05.2018 at 16:02.
 */
@Component
public class AnomalyDetector {

    @Autowired
    Logger logger;

    @Autowired
    IDataManager dataManager;

    @Autowired
    MessagePresenter messagePresenter;

    @PostConstruct
    public void startAnomalyDetector(){

        logger.info("AnomalyDetector is started!");

        Observable.interval(60, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .subscribe(aLong -> {
                    List<Channel> allChannels = getChannels();
                    for (Channel channel: allChannels) {
                        checkAnomalyCondition(channel);
                    }
                });
    }

    private void checkAnomalyCondition(Channel channel) throws IOException {
        List<BloodSugarData> preBloodSugarData = getRecentPreBloodSugarData(channel.owner);
        List<BloodSugarData> postBloodSugarData = getRecentPostBloodSugarData(channel.owner);

        Message message = new Message();
        message.createdAt = new Date();
        message.toChannelId = channel.id;
        message.id = UUID.randomUUID().toString();
        message.senderUserId = "SUCRE";
        message.messageText = channel.channelName + " : ";

        if (isDataViableForAnalyse(preBloodSugarData)){
            if (preBloodSugarData.get(0).value < 50 &&
                preBloodSugarData.get(1).value < 50 &&
                preBloodSugarData.get(2).value < 50 &&
                preBloodSugarData.get(3).value < 50 &&
                preBloodSugarData.get(4).value < 50 ){

                message.messageText += "AÇLIK ŞEKERİNDE AŞIRI DÜŞME VAR!";
                messagePresenter.distributeMessage(message);
                logger.debug(message.messageText);
            } else if(preBloodSugarData.get(0).value > 140 &&
                    preBloodSugarData.get(1).value > 140 &&
                    preBloodSugarData.get(2).value > 140 &&
                    preBloodSugarData.get(3).value > 140 &&
                    preBloodSugarData.get(4).value > 140 ){

                message.messageText += "AÇLIK ŞEKERİNDE AŞIRI YÜKSELME VAR!";
                messagePresenter.distributeMessage(message);
                logger.debug(message.messageText);
            }
        }

        if (isDataViableForAnalyse(postBloodSugarData)){
            if (postBloodSugarData.get(0).value < 100 &&
                postBloodSugarData.get(1).value < 100 &&
                postBloodSugarData.get(2).value < 100 &&
                postBloodSugarData.get(3).value < 100 &&
                postBloodSugarData.get(4).value < 100){

                message.messageText += "TOKLUK ŞEKERİNDE AŞIRI DÜŞME VAR!";
                messagePresenter.distributeMessage(message);
                logger.debug(message.messageText);
            } else if(postBloodSugarData.get(0).value > 250 &&
                    postBloodSugarData.get(1).value > 250 &&
                    postBloodSugarData.get(2).value > 250 &&
                    postBloodSugarData.get(3).value > 250 &&
                    postBloodSugarData.get(4).value > 250){

                message.messageText += "TOKLUK ŞEKERİNDE AŞIRI YÜKSELME VAR!";
                messagePresenter.distributeMessage(message);
                logger.debug(message.messageText);
            }
        }

    }

    private boolean isDataViableForAnalyse(List<BloodSugarData> bloodSugarDataList) {
        return bloodSugarDataList.size() > 5 &&
                isLast5HoursData(bloodSugarDataList.get(0).date, bloodSugarDataList.get(4).date);
    }

    public boolean isLast5HoursData(Date startDate, Date endDate){
        long start = startDate.getTime();
        long end = endDate.getTime();

        return Math.abs(end - start) < (5 * 60 * 60 * 1000);
    }

    private List<Channel> getChannels(){
        return dataManager.getChannels();
    }

    private List<BloodSugarData> getRecentPreBloodSugarData(String userId){
        return dataManager.getBloodSugar(userId, BloodSugarData.SugarMeasurementType.PRE);
    }

    private List<BloodSugarData> getRecentPostBloodSugarData(String userId){
        return dataManager.getBloodSugar(userId, BloodSugarData.SugarMeasurementType.POST);
    }

}
