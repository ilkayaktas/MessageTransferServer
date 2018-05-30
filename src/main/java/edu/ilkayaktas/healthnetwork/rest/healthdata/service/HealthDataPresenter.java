package edu.ilkayaktas.healthnetwork.rest.healthdata.service;

import edu.ilkayaktas.healthnetwork.controller.IDataManager;
import edu.ilkayaktas.healthnetwork.model.db.BloodSugarData;
import edu.ilkayaktas.healthnetwork.model.db.HealthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by aselsan on 7.05.2018 at 17:08.
 */
@Service
public class HealthDataPresenter {

    private final IDataManager dataManager;

    @Autowired
    public HealthDataPresenter(IDataManager dataManager) {
        this.dataManager = dataManager;
    }

    public HealthData saveHealthData(HealthData healthData){
        return dataManager.saveHealthData(healthData);
    }

    public List<HealthData> getHealthData(String userId, HealthData.HealthDataType healthDataType){
        return dataManager.getHealthData(userId, healthDataType);
    }

    public BloodSugarData saveBloodSugar(BloodSugarData bloodSugarData){
        return dataManager.saveBloodSugar(bloodSugarData);
    }

    public List<BloodSugarData> getBloodSugar(String userId, BloodSugarData.SugarMeasurementType sugarMeasurementType){
        return dataManager.getBloodSugar(userId, sugarMeasurementType);
    }

    public BloodSugarData deleteBloodSugar(String bloodSugarId){
        return dataManager.deleteBloodSugar(bloodSugarId);
    }
}
