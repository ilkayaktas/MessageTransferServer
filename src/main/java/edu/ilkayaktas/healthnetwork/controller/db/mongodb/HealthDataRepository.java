package edu.ilkayaktas.healthnetwork.controller.db.mongodb;

import edu.ilkayaktas.healthnetwork.model.db.HealthData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ilkayaktas on 22.04.2018 at 13:34.
 */

public interface HealthDataRepository extends MongoRepository<HealthData, String> {
    List<HealthData> getHealthDataByToUserIdAndHealthDataType(String userId, HealthData.HealthDataType healthDataType);
}
