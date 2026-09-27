package com.forecast_fits.forecast_fits_backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.forecast_fits.forecast_fits_backend.model.dto.brightsky.BrightSkyCurrentResponse;
import com.forecast_fits.forecast_fits_backend.model.dto.brightsky.BrightSkyForecastResponse;
import com.forecast_fits.forecast_fits_backend.model.dto.weather.WeatherCurrentDTO;
import com.forecast_fits.forecast_fits_backend.model.dto.weather.WeatherForecastDTO;
import com.forecast_fits.forecast_fits_backend.model.vm.HomepageRecommendationVM;
import com.forecast_fits.forecast_fits_backend.model.vm.WeatherCurrentVM;
import com.forecast_fits.forecast_fits_backend.model.vm.WeatherForecastVM;

import lombok.AllArgsConstructor;

@Service 
@AllArgsConstructor 
public class HomepageService {

    private final WeatherService weatherService;

    public HomepageRecommendationVM getRecommendation(Double lat, Double lon, String location) {

        String currentDate = LocalDate.now().toString();

        BrightSkyCurrentResponse weatherResponse = weatherService.getCurrentWeather(lat, lon);
        BrightSkyForecastResponse forecastResponse = weatherService.getForecast(lat, lon, currentDate, null);

        WeatherCurrentDTO weatherDTO = weatherResponse.getWeather();
        List<WeatherForecastDTO> weatherForecast = forecastResponse.getWeather();
        List<WeatherForecastVM> forecastVMs = weatherForecast.stream().map(WeatherForecastVM::fromModel).toList();
        String finalLocation = (location != null && !location.isBlank()) 
                           ? location 
                           : null;
        return HomepageRecommendationVM.builder()
            .weather(
                WeatherCurrentVM.builder()
                    .location(finalLocation)
                    .cloudCover(weatherDTO.getCloudCover())
                    .condition(weatherDTO.getCondition())
                    .relativeHumidity(weatherDTO.getRelativeHumidity())
                    .temperature(weatherDTO.getTemperature())
                    .windSpeed(weatherDTO.getWindSpeed10())
                    .build()
            )
            .forecast(forecastVMs)
            .build();
    }

    public List<WeatherForecastVM> getForecastWeek(Double lat, Double lon) {

        String currentDate = LocalDate.now().toString();
        String endDate = LocalDate.now().plusWeeks(1).toString();
        System.out.println("currentDate: " + currentDate + ", endDate: " + endDate);
        BrightSkyForecastResponse forecastResponse = weatherService.getForecast(lat, lon, currentDate, endDate);
        List<WeatherForecastDTO> weatherForecast = forecastResponse.getWeather();
        
        return weatherForecast.stream().map(WeatherForecastVM::fromModel).toList();
    }
}
