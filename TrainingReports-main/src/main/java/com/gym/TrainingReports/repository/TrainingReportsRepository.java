package com.gym.TrainingReports.repository;

import com.gym.TrainingReports.model.ActivityReports;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface TrainingReportsRepository extends MongoRepository<ActivityReports, Integer> {
    List<ActivityReports> findReportsBytraineeId(int learnerId);
    List<ActivityReports> findReportsBytraineeEmail(String traineeEmail);
}
