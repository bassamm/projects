package com.sample.trainingacademy;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({
        "com.sample.trainingacademy.repository",
        "com.sample.trainingacademy.service"
})
public class TrainingAcademyTests {
}
