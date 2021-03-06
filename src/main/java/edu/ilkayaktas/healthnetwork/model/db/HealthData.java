package edu.ilkayaktas.healthnetwork.model.db;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by ilkayaktas on 22.04.2018 at 13:14.
 */

public class HealthData {
    @Id
    public String id; // message id
    public Date date;
    public String userId;
    public String dataText;
    public String dataTextDetail;
    public HealthDataType healthDataType;

    public enum HealthDataType{
        ALL(0),
        TREATMENT(1),
        NUTRITION(2),
        ACTIVITY(3);

        private final int value;

        HealthDataType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
