package com.forecast_fits.forecast_fits_backend.model.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.forecast_fits.forecast_fits_backend.model.dto.FallbackSourceDTO;

import lombok.Data;

@Data
public class WeatherCurrentDTO {
    
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
    
    @JsonProperty("precipitation_10")
    Double precipitation10;
    
    @JsonProperty("precipitation_30")
    Double precipitation30;
    
    @JsonProperty("precipitation_60")
    Double precipitation60;
    
    @JsonProperty("solar_10")
    Double solar10;
    
    @JsonProperty("solar_30")
    Double solar30;
    
    @JsonProperty("solar_60")
    Double solar60;
    
    @JsonProperty("sunshine_30")
    Integer sunshine30;
    
    @JsonProperty("sunshine_60")
    Integer sunshine60;
    
    @JsonProperty("wind_direction_10")
    Integer windDirection10;
    
    @JsonProperty("wind_direction_30")
    Integer windDirection30;
    
    @JsonProperty("wind_direction_60")
    Integer windDirection60;
    
    @JsonProperty("wind_speed_10")
    Double windSpeed10;
    
    @JsonProperty("wind_speed_30")
    Double windSpeed30;
    
    @JsonProperty("wind_speed_60")
    Double windSpeed60;
    
    @JsonProperty("wind_gust_direction_10")
    Integer windGustDirection10;
    
    @JsonProperty("wind_gust_direction_30")
    Integer windGustDirection30;
    
    @JsonProperty("wind_gust_direction_60")
    Integer windGustDirection60;
    
    @JsonProperty("wind_gust_speed_10")
    Double windGustSpeed10;
    
    @JsonProperty("wind_gust_speed_30")
    Double windGustSpeed30;
    
    @JsonProperty("wind_gust_speed_60")
    Double windGustSpeed60;
}