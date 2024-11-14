package com.example.controller;

import com.example.dto.CarDto;
import com.example.model.CarModel;
import com.example.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;

@Controller
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    public String getAllCars(Model model) {
        List<CarModel> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "car/list";
    }

    public String showCreateForm(Model model) {
        model.addAttribute("car", new CarDto());
        return "car/create";
    }

    public String createCar(CarDto carDto) {
        carService.createCar(carDto);
        return "redirect:/cars";
    }

    public String getCarById(Long id, Model model) {
        CarModel car = carService.getCarById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        model.addAttribute("car", car);
        return "car/view";
    }

    public String showUpdateForm(Long id, Model model) {
        CarModel car = carService.getCarById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        model.addAttribute("car", car);
        return "car/edit";
    }

    public String updateCar(Long id, CarDto carDto) {
        carService.updateCar(id, carDto);
        return "redirect:/cars";
    }

    public String deleteCar(Long id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }
}