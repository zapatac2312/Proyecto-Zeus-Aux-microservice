package com.gym.TrainingReports.service;

import com.gym.TrainingReports.exception.TrainingReportNotFoundException;
import com.gym.TrainingReports.model.TrainingReports;
import com.gym.TrainingReports.repository.TrainingReportsRepository;
import com.gym.TrainingReports.service.TrainingReportsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@DataJpaTest
public class TrainingReportsServiceTest {
    @Autowired
    private TrainingReportsService trainingReportsService;

    @Autowired
    private TrainingReportsRepository trainingReportsRepository;

    @Test
    void getMonthlyReports_WhenNoReportsFound_ShouldThrowException() {
        String traineeEmail = "aasd";
        int month = 1;
        int year = 2022;
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        when(trainingReportsRepository.findReportsBytraineeEmail(traineeEmail))
                .thenReturn(Collections.emptyList());

        assertThatThrownBy(() -> trainingReportsService.getMonthlyReports(traineeEmail, month, year))
                .isInstanceOf(TrainingReportNotFoundException.class)
                .hasMessageContaining("No se encontraron informes de entrenamiento");
    }
}
