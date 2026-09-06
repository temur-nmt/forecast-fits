package com.forecast_fits.forecast_fits_backend.model.dto.brightsky;

import com.forecast_fits.forecast_fits_backend.model.dto.weather.SourceDTO;
import com.forecast_fits.forecast_fits_backend.model.dto.weather.WeatherCurrentDTO;

import lombok.Data;

@Data 
public class BrightSkyCurrentResponse {
    WeatherCurrentDTO weather;
    SourceDTO source;
}
