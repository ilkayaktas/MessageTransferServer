package edu.ilkayaktas.healthnetwork.model.db;

import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by iaktas on 24.04.2017.
 */
@ToString
public class BloodSugarData {
    @Id
    public String id; // message id
    public Date date;
    public int value;
    public String userId;
    public SugarMeasurementType sugarMeasurementType; // 1 means pre, 2 means post

    public BloodSugarData(String id, Date date, int value, SugarMeasurementType sugarMeasurementType, String userId) {
        this.id = id;
        this.date = date;
        this.value = value;
        this.sugarMeasurementType = sugarMeasurementType;
        this.userId = userId;
    }

    public BloodSugarData() {
    }

    public enum SugarMeasurementType {
        ALL(0),
        PRE(1),
        POST(2);

        private int value;

        SugarMeasurementType(int value) {
            this.value = value;
        }

        public int getValue(){return value;}


    }

}
