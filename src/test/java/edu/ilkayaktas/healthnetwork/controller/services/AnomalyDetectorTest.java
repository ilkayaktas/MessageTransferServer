package edu.ilkayaktas.healthnetwork.controller.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by ilkayaktas on 7.05.2018 at 21:48.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(AnomalyDetector.class)
public class AnomalyDetectorTest {

    @Autowired
    AnomalyDetector anomalyDetector;

    @Test
    public void isLast5HoursData() {
        Date start = new Date();
        Date stop = new Date();
        Assert.assertTrue(anomalyDetector.isLast5HoursData(start, stop));
    }
}