package com.forecast_fits.forecast_fits_backend.model.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.forecast_fits.forecast_fits_backend.model.dto.FallbackSourceDTO;

import lombok.Data;

@Data
public class WeatherForecastDTO {
    
    @JsonProperty("timestamp")
    String timestamp;
    
    @JsonProperty("source_id")
    Integer sourceId;
    
    @JsonProperty("cloud_cover")
    Double cloudCover;
    
    @JsonProperty("condition")
    String condition;
    
    @JsonProperty("dew_point")
    Double dewPoint;
    
    @JsonProperty("icon")
    String icon;
    
    @JsonProperty("pressure_msl")
    Double preassureMsl;
    
    @JsonProperty("relative_humidity")
    Integer relativeHumidity;
    
    @JsonProperty("temperature")
    Double temperature;
    
    @JsonProperty("visibility")
    Integer Visibility;
    
    @JsonProperty("fallback_source_ids")
    FallbackSourceDTO FallbackSourceIds;
    
    @JsonProperty("precipitation")
    Double precipitation;
    
    @JsonProperty("solar")
    Double solar;
    
    @JsonProperty("sunshine")
    Integer sunshine;
    
    @JsonProperty("wind_direction")
    Integer windDirection;
    
    @JsonProperty("wind_speed")
    Double windSpeed;
    
    @JsonProperty("wind_gust_direction")
    Integer windGustDirection;
    
    @JsonProperty("wind_gust_speed")
    Double windGustSpeed;
    
    @JsonProperty("precipitation_probability")
    Integer precipitationProbability;
    
    @JsonProperty("precipitation_probability_6h")
    Integer precipitationProbability6h;
}