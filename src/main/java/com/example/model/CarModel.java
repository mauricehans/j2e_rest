package com.example.model;
import java.time.LocalDate;


public class CarModel {

    private Long id;
    private String model;
    private String brand;
    private LocalDate releaseDate;
    private String status;

    public CarModel() {}

    public CarModel(String model, String brand, LocalDate releaseDate, String status) {
        this.model = model;
        this.brand = brand;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    // Getters
    public Long getId() { return id; }
    public String getModel() { return model; }
    public String getBrand() { return brand; }
    public LocalDate getReleaseDate() { return releaseDate; }
    public String getStatus() { return status; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setModel(String model) { this.model = model; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }
    public void setStatus(String status) { this.status = status; }
}
