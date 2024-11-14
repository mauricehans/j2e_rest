package com.example.dto;

public class CarDto {
    private String model;
    private String brand;
    private String releaseDate;
    private String status;

    // Constructeur par défaut
    public CarDto() {}

    // Constructeur avec paramètres
    public CarDto(String model, String brand, String releaseDate, String status) {
        this.model = model;
        this.brand = brand;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    // Getters
    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getStatus() {
        return status;
    }

    // Setters
    public void setModel(String model) {
        this.model = model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CarDto{" +
                "model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}