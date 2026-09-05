package com.forecast_fits.forecast_fits_backend.model.dto;

import java.util.Set;

import com.forecast_fits.forecast_fits_backend.enums.ClothesCategory;
import com.forecast_fits.forecast_fits_backend.enums.Weather;

import lombok.Data;

@Data 
public class ClothesDTO {
    private String name;
    private int userId;
    private Set<ClothesCategory> categories;
    private int minTemp;
    private int maxTemp;
    private Set<Weather> weather;
}
