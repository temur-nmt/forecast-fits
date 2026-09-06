package com.forecast_fits.forecast_fits_backend.model.dto.brightsky;

import com.forecast_fits.forecast_fits_backend.model.dto.weather.SourceDTO;
import com.forecast_fits.forecast_fits_backend.model.dto.weather.WeatherForecastDTO;

import lombok.Data;

@Data 
public class BrightSkyForecastResponse {
    WeatherForecastDTO weather;
    SourceDTO source;
}
