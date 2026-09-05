package com.forecast_fits.forecast_fits_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.forecast_fits.forecast_fits_backend.model.entity.Clothes;

public interface ClothesRepository extends JpaRepository<Clothes, Integer> {
    Optional<List<Clothes>> findByName(String name);
    Optional<List<Clothes>> findByUserId(int userId);
}
