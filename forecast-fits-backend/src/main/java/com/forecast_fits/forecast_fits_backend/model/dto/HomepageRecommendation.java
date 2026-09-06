package com.forecast_fits.forecast_fits_backend.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder 
public class HomepageRecommendation {
    private String location;
    private Double cloudCover;
    private String condition;
    private Integer relativeHumidity;
    private Double temperature;
    private Double windSpeed;
}
