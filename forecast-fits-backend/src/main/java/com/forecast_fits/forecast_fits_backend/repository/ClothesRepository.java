package com.forecast_fits.forecast_fits_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.forecast_fits.forecast_fits_backend.enums.ClothesCategory;
import com.forecast_fits.forecast_fits_backend.model.entity.Clothes;

public interface ClothesRepository extends JpaRepository<Clothes, Integer> {
    List<Clothes> findByName(String name);
    List<Clothes> findByUserId(int userId);
    
    // this method only applies to MySQL
    @Query(
        value = """
            SELECT * FROM clothes
            WHERE user_id = :userId
            AND categories IN (:categories)
            AND :currentTemp BETWEEN min_temp AND max_temp
            AND JSON_OVERLAPS(weather, :weather) = 1
        """,
        nativeQuery = true
    )
    List<Clothes> findFit(
        @Param("userId") int userId, 
        @Param("currentTemp") int currentTemp, 
        @Param("weather") String weather, 
        @Param("categories") List<ClothesCategory> categories
    );
}
