/*package com.gym.TrainingReports.controller;

import com.gym.TrainingReports.controller.TrainingReportsController;
import com.gym.TrainingReports.service.TrainingReportsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.Mockito.when;

@WebMvcTest(TrainingReportsController.class)
@AutoConfigureMockMvc
public class TrainingReportsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainingReportsService trainingReportsService;

    @Test
    void getMonthlyReports_WhenNoReportsFound_ShouldReturnEmptyList() throws Exception {
        String traineeEmail = "asd";
        int month = 1;
        int year = 2022;
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        when(trainingReportsService.getMonthlyReports(traineeEmail, month, year))
                .thenReturn(Collections.emptyList().toString());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/training-reports/monthly-reports/{idLearner}/{month}/{year}", idTrainee, month, year)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }
}*/
