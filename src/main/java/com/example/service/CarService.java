package com.example.service;

import com.example.dto.CarDto;
import com.example.model.CarModel;
import com.example.repository.CarRepository;
import com.example.exception.CarException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    /**
     * Create a new car from DTO
     */
    public CarModel createCar(CarDto carDto) {
        try {
            CarModel car = convertToModel(carDto);
            return carRepository.save(car);
        } catch (Exception e) {
            throw new CarException("Error creating car: " + e.getMessage());
        }
    }

    /**
     * Get all cars
     */
    public List<CarModel> getAllCars() {
        try {
            return carRepository.findAll();
        } catch (Exception e) {
            throw new CarException("Error retrieving cars: " + e.getMessage());
        }
    }

    /**
     * Get car by ID
     */
    public Optional<CarModel> getCarById(Long id) {
        try {
            return carRepository.findById(id);
        } catch (Exception e) {
            throw new CarException("Error retrieving car with id " + id + ": " + e.getMessage());
        }
    }

    /**
     * Update existing car
     */
    public CarModel updateCar(Long id, CarDto carDto) {
        try {
            return carRepository.findById(id)
                .map(existingCar -> {
                    updateCarFromDto(existingCar, carDto);
                    return carRepository.save(existingCar);
                })
                .orElseThrow(() -> new CarException("Car not found with id: " + id));
        } catch (Exception e) {
            throw new CarException("Error updating car with id " + id + ": " + e.getMessage());
        }
    }

    /**
     * Delete car by ID
     */
    public void deleteCar(Long id) {
        try {
            if (!carRepository.findById(id).isPresent()) {
                throw new CarException("Car not found with id: " + id);
            }
            carRepository.deleteById(id);
        } catch (Exception e) {
            throw new CarException("Error deleting car with id " + id + ": " + e.getMessage());
        }
    }

    /**
     * Get cars by status
     */
    public List<CarModel> getCarsByStatus(String status) {
        try {
            return getAllCars().stream()
                .filter(car -> status.equals(car.getStatus()))
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new CarException("Error retrieving cars with status " + status + ": " + e.getMessage());
        }
    }

    /**
     * Get available cars
     */
    public List<CarModel> getAvailableCars() {
        return getCarsByStatus("available");
    }

    /**
     * Convert DTO to Model
     */
    private CarModel convertToModel(CarDto carDto) {
        return new CarModel(
            carDto.getModel(),
            carDto.getBrand(),
            LocalDate.parse(carDto.getReleaseDate()),
            carDto.getStatus()
        );
    }

    /**
     * Update car model from DTO
     */
    private void updateCarFromDto(CarModel car, CarDto carDto) {
        car.setModel(carDto.getModel());
        car.setBrand(carDto.getBrand());
        car.setReleaseDate(LocalDate.parse(carDto.getReleaseDate()));
        car.setStatus(carDto.getStatus());
    }

    /**
     * Check if car exists
     */
    public boolean existsById(Long id) {
        try {
            return carRepository.findById(id).isPresent();
        } catch (Exception e) {
            throw new CarException("Error checking car existence with id " + id + ": " + e.getMessage());
        }
    }

    /**
     * Change car status
     */
    public CarModel changeCarStatus(Long id, String newStatus) {
        try {
            return carRepository.findById(id)
                .map(car -> {
                    car.setStatus(newStatus);
                    return carRepository.save(car);
                })
                .orElseThrow(() -> new CarException("Car not found with id: " + id));
        } catch (Exception e) {
            throw new CarException("Error changing status for car with id " + id + ": " + e.getMessage());
        }
    }

    /**
     * Count total cars
     */
    public long countCars() {
        try {
            return getAllCars().size();
        } catch (Exception e) {
            throw new CarException("Error counting cars: " + e.getMessage());
        }
    }

    /**
     * Count cars by status
     */
    public long countCarsByStatus(String status) {
        try {
            return getCarsByStatus(status).size();
        } catch (Exception e) {
            throw new CarException("Error counting cars by status " + status + ": " + e.getMessage());
        }
    }
}