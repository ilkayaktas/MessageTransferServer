package edu.ilkayaktas.healthnetwork.rest.healthdata;

import edu.ilkayaktas.healthnetwork.model.db.BloodSugarData;
import edu.ilkayaktas.healthnetwork.model.db.HealthData;
import edu.ilkayaktas.healthnetwork.model.utils.AppConstants;
import edu.ilkayaktas.healthnetwork.rest.healthdata.service.HealthDataPresenter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by aselsan on 7.05.2018 at 17:07.
 */

@RestController
public class HealthDataController {

    @Autowired
    Logger logger;

    @Autowired
    HealthDataPresenter healthDataPresenter;

    @RequestMapping(value = "/healthdata/save", method = RequestMethod.POST)
    public ResponseEntity<HealthData> saveHealthData(@RequestBody HealthData healthData) {
        logger.info("healthdata/send"+" healthData:"+healthData);
        HealthData healthDataReturned = healthDataPresenter.saveHealthData(healthData);
        return new ResponseEntity<>(healthDataReturned, AppConstants.HTTP_STATUS_OK);
    }

    @RequestMapping(value = "/healthdata/get", method = RequestMethod.GET)
    public ResponseEntity<List<HealthData>> getHealthData(@RequestParam("userId") String userId, @RequestParam("healthDataType") String healthDataType) {
        logger.info("healthdata/get"+" userId:"+userId + " healthDataType:"+healthDataType);
        List<HealthData> healthDataList = healthDataPresenter.getHealthData(userId, HealthData.HealthDataType.valueOf(healthDataType));
        return new ResponseEntity<>(healthDataList, AppConstants.HTTP_STATUS_OK);
    }

    @RequestMapping(value = "/bloodsugar/save", method = RequestMethod.POST)
    public ResponseEntity<BloodSugarData> saveBloodSugar(@RequestBody BloodSugarData bloodSugarData) {
        logger.info("bloodsugar/save"+" bloodSugarData:"+ bloodSugarData.toString());
        BloodSugarData bloodSugarDataReturned = healthDataPresenter.saveBloodSugar(bloodSugarData);
        return new ResponseEntity<>(bloodSugarDataReturned, AppConstants.HTTP_STATUS_OK);
    }

    @RequestMapping(value = "/bloodsugar/get", method = RequestMethod.GET)
    public ResponseEntity<List<BloodSugarData>> getBloodSugar(@RequestParam("userId") String userId, @RequestParam("sugarMeasurementType") String sugarMeasurementType) {
        logger.info("bloodsugar/get"+" userId:"+userId + " sugarMeasurementType:"+sugarMeasurementType);
        List<BloodSugarData> healthDataList = healthDataPresenter.getBloodSugar(userId, BloodSugarData.SugarMeasurementType.valueOf(sugarMeasurementType));
        return new ResponseEntity<>(healthDataList, AppConstants.HTTP_STATUS_OK);
    }

    @RequestMapping(value = "/bloodsugar/delete", method = RequestMethod.DELETE)
    public ResponseEntity<BloodSugarData> deleteBloodSugar(@RequestParam("bloodSugarId") String bloodSugarId) {
        logger.info("bloodsugar/delete"+" bloodSugarId:"+bloodSugarId);
        BloodSugarData bloodSugar = healthDataPresenter.deleteBloodSugar(bloodSugarId);
        return new ResponseEntity<>(bloodSugar, AppConstants.HTTP_STATUS_OK);
    }
}
