package com.forecast_fits.forecast_fits_backend.model.vm;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor 
@Builder
public class WeatherCurrentVM {
    
    private final String location;
    private final Double cloudCover;
    private final String condition;
    private final Integer relativeHumidity;
    private final Double temperature;
    private final Double windSpeed;

}
