package com.gym.TrainingReports.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("trainingReports")
public class ActivityReports {
    @Id
    private Integer traineeId;
    private Integer trainerId;
    private String traineeName;
    private String TrainerName;
    private LocalDate trainingDate;
    private String trainingType;
    private String duration;
    private String traineeEmail;

    public boolean isValidTrainingDate() {
        return trainingDate != null && trainingDate.isBefore(LocalDate.now());
    }

    public boolean isValidTrainingDuration() {
        return duration != null && !duration.isEmpty();
    }

    public boolean isValid() {
        return isValidTrainingDate() && isValidTrainingDuration();
    }
}