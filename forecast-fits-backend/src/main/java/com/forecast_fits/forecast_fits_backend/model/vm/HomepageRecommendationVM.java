package com.forecast_fits.forecast_fits_backend.model.vm;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder 
public class HomepageRecommendationVM {
    private WeatherCurrentVM weather;
    private List<WeatherForecastVM> forecast;
    private FitReccomendationVM recomendation;
}
