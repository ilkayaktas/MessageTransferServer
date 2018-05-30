package edu.ilkayaktas.healthnetwork.controller.db.mongodb;

import edu.ilkayaktas.healthnetwork.model.db.BloodSugarData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ilkayaktas on 22.04.2018 at 13:54.
 */

public interface BloodSugarRepository extends MongoRepository<BloodSugarData, String> {
    List<BloodSugarData> getBloodSugarByUserIdOrderByDateDesc(String userId);
    List<BloodSugarData> getBloodSugarByUserIdAndSugarMeasurementTypeOrderByDateDesc(String userId, BloodSugarData.SugarMeasurementType sugarMeasurementType);
}
