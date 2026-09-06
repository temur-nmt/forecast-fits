package com.forecast_fits.forecast_fits_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.forecast_fits.forecast_fits_backend.model.dto.brightsky.BrightSkyCurrentResponse;
import com.forecast_fits.forecast_fits_backend.model.dto.brightsky.BrightSkyForecastResponse;

@Service 
public class WeatherService {
    
    private final RestClient restClient;

    public WeatherService() {
        this.restClient = RestClient.create();
    }

    public BrightSkyCurrentResponse getCurrentWeather(Double lat, Double lon) {
        return  restClient.get()
            .uri("https://api.brightsky.dev/current_weather?lat=" + lat + "&lon=" + lon)
            .retrieve()
            .body(BrightSkyCurrentResponse.class);
    }

    public BrightSkyForecastResponse getForecast(int lat, int lon, String date, String lastDate) {
        return restClient.get()
            .uri("https://api.brightsky.dev/weather?" + "date=" + date + "&last_date=" + lastDate + "&lat=" + lat + "&lon=" + lon)
            .retrieve()
            .body(BrightSkyForecastResponse.class);
    }
}
