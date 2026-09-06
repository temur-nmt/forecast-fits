package com.forecast_fits.forecast_fits_backend.service;

import org.springframework.stereotype.Service;

import com.forecast_fits.forecast_fits_backend.model.dto.HomepageRecommendation;
import com.forecast_fits.forecast_fits_backend.model.dto.brightsky.BrightSkyCurrentResponse;
import com.forecast_fits.forecast_fits_backend.model.dto.weather.WeatherCurrentDTO;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class HomepageService {

    private final WeatherService weatherService;

    public HomepageRecommendation getRecommendation(Double lat, Double lon, String location) {
        BrightSkyCurrentResponse response = weatherService.getCurrentWeather(lat, lon);
        WeatherCurrentDTO weather = response.getWeather();
        return HomepageRecommendation.builder()
            .location(location)
            .cloudCover(weather.getCloudCover())
            .condition(weather.getCondition())
            .relativeHumidity(weather.getRelativeHumidity())
            .temperature(weather.getTemperature())
            .windSpeed(weather.getWindSpeed10())
            .build();
    }

    // public HomepageRecommendation getForecast(int lat, int lon, String date, String lastDate) {
        
    // }
}
