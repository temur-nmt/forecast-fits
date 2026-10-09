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
            .uri(uriBuilder -> {
                uriBuilder.scheme("https")
                        .host("api.brightsky.dev")
                        .path("/current_weather")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon);
        
                return uriBuilder.build();
            })
            .retrieve()
            .body(BrightSkyCurrentResponse.class);
    }

    public BrightSkyForecastResponse getForecast(Double lat, Double lon, String date, String lastDate) {

        return restClient.get()
            .uri(uriBuilder -> {
                uriBuilder.scheme("https")
                        .host("api.brightsky.dev")
                        .path("/weather")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .queryParam("date", date);
            
                if (lastDate != null) {
                    uriBuilder.queryParam("last_date", lastDate);
                }
                return uriBuilder.build();
            })
            .retrieve()
            .body(BrightSkyForecastResponse.class);
    }
}
