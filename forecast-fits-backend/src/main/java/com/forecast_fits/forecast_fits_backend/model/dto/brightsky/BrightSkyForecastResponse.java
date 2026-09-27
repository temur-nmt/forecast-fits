package com.forecast_fits.forecast_fits_backend.model.dto.brightsky;

import com.forecast_fits.forecast_fits_backend.model.dto.weather.SourceDTO;
import com.forecast_fits.forecast_fits_backend.model.dto.weather.WeatherForecastDTO;

import lombok.Data;

import java.util.List;

@Data 
public class BrightSkyForecastResponse {
    List<WeatherForecastDTO> weather;
    List<SourceDTO> source;
}
