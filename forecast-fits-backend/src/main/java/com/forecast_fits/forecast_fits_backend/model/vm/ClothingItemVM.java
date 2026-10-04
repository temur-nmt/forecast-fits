package com.forecast_fits.forecast_fits_backend.model.vm;

import com.forecast_fits.forecast_fits_backend.model.entity.Clothes;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor 
@Builder
public class ClothingItemVM {
    private final String name;
    private final int minTemp;
    private final int maxTemp;

    public static ClothingItemVM fromModel(Clothes clothes) {
        return ClothingItemVM.builder()
                .name(clothes.getName())
                .maxTemp(clothes.getMaxTemp())
                .minTemp(clothes.getMinTemp())
                .build();
    }
}
