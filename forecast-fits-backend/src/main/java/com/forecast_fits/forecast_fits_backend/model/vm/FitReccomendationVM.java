package com.forecast_fits.forecast_fits_backend.model.vm;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor 
@Builder
public class FitReccomendationVM {
    private final ClothingItemVM baseLayer;
    private final ClothingItemVM midLayer;
    private final ClothingItemVM outerLayer;

    private final ClothingItemVM bottom;
    private final ClothingItemVM footwear;
    private final ClothingItemVM headwear;

    private final List<ClothingItemVM> accessoires;
}
