package edu.ilkayaktas.healthnetwork.controller.db.mongodb;

import edu.ilkayaktas.healthnetwork.model.db.BloodSugar;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by ilkayaktas on 22.04.2018 at 13:54.
 */

public interface BloodSugarRepository extends MongoRepository<BloodSugar, String> {
    List<BloodSugar> getBloodSugarByUserIdAndSugarMeasurementType(String userId, BloodSugar.SugarMeasurementType sugarMeasurementType);
}
