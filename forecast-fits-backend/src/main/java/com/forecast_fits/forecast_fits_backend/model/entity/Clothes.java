package com.forecast_fits.forecast_fits_backend.model.entity;

import java.util.Set;

import com.forecast_fits.forecast_fits_backend.enums.ClothesCategory;
import com.forecast_fits.forecast_fits_backend.enums.Weather;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@Table(name = "clothes")
@NoArgsConstructor  
public class Clothes {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="userId")
    private int userId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Enumerated(EnumType.STRING)
    @Column(name="categories")
    private Set<ClothesCategory> categories;

    @Column(name="minTemp")
    private int min;

    @Column(name="maxTemp")
    private int max;

    @JdbcTypeCode(SqlTypes.JSON)
    @Enumerated(EnumType.STRING)
    @Column(name="weather")
    Set<Weather> weather;
}
