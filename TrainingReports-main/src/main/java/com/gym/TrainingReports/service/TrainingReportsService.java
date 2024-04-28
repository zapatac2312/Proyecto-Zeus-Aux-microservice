package com.gym.TrainingReports.service;

import com.gym.TrainingReports.exception.TrainingReportNotFoundException;
import com.gym.TrainingReports.model.ActivityReports;
import com.gym.TrainingReports.repository.TrainingReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrainingReportsService {
    private final TrainingReportsRepository trainingReportsRepository;

    @Autowired
    public TrainingReportsService(TrainingReportsRepository trainingReportsRepository) {
        this.trainingReportsRepository = trainingReportsRepository;
    }

    public void saveTrainingReports(ActivityReports activityReports) {
        trainingReportsRepository.save(activityReports);
    }

    // Obtener el reporte mensual
    public String getMonthlyReports(String traineeEmail, int month, int year) {
        // Determinamos fecha de inicio y fin
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        // Obtener informes mensuales
        List<ActivityReports> reports = trainingReportsRepository.findReportsBytraineeEmail(traineeEmail);

        // Lanza una excepción sí no se encuentran informes
        if (reports.isEmpty()) {
            throw new TrainingReportNotFoundException("No se encontraron informes de entrenamiento para el aprendiz en el mes y año especificados.");
        }

        // Se crea un StringBuilder para construir el reporte mensual
        StringBuilder monthlyReport = new StringBuilder();
        monthlyReport.append("Reporte Mensual del Aprendiz con correo electronico: ").append(traineeEmail).append(" (").append(Month.of(month)).append(" ").append(year).append("):\n");

        // Se agrupan los informes por semana
        Map<Integer, List<ActivityReports>> weeklyReports = reports.stream()
                .collect(Collectors.groupingBy(report -> report.getTrainingDate().get(ChronoField.ALIGNED_WEEK_OF_MONTH)));

        // Se itera sobre las semanas para agregar los informes de entrenamiento
        weeklyReports.forEach((week, weekReports) -> {
            monthlyReport.append("Semana ").append(week).append(":\n");
            weekReports.forEach(report -> {
                monthlyReport.append("- ").append(report.getTrainingDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                        .append(", ").append("Entrenamiento de ").append(report.getTrainingType()).append("\n");
            });
        });

        return monthlyReport.toString(); // Retornar reporte mensual
    }
}
