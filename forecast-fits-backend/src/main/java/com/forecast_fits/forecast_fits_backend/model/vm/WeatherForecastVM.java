package com.forecast_fits.forecast_fits_backend.model.vm;

import com.forecast_fits.forecast_fits_backend.model.dto.weather.WeatherForecastDTO;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor 
@Builder
public class WeatherForecastVM {
    private final String time;
    private final Double cloudCover;
    private final String condition;
    private final Double temperature;
    private final Double windSpeed;

    public static WeatherForecastVM fromModel(WeatherForecastDTO dto) {
        return WeatherForecastVM.builder()
            .time(dto.getTimestamp())
            .cloudCover(dto.getCloudCover())
            .condition(dto.getCondition())
            .temperature(dto.getTemperature())
            .windSpeed(dto.getWindSpeed())
            .build();
    }
}
