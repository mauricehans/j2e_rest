package com.example.repository;

import com.example.model.CarModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CarRepository {

    private final ConcurrentHashMap<Long, CarModel> cars = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Sauvegarder une voiture (ajout ou mise à jour)
    public CarModel save(CarModel car) {
        if (car.getId() == null) {
            car.setId(idGenerator.getAndIncrement());  // Génère un nouvel ID si non défini
        }
        cars.put(car.getId(), car);  // Ajoute ou met à jour la voiture dans la collection
        return car;
    }

    // Trouver toutes les voitures
    public List<CarModel> findAll() {
        return new ArrayList<>(cars.values());
    }

    // Trouver une voiture par ID
    public Optional<CarModel> findById(Long id) {
        return Optional.ofNullable(cars.get(id));
    }

    // Supprimer une voiture par ID
    public void deleteById(Long id) {
        cars.remove(id);
    }

    // Vérifier si une voiture existe par ID
    public boolean existsById(Long id) {
        return cars.containsKey(id);
    }
}
