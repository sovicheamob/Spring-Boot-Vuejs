package com.vehicle.springbootvuejs.service;

import com.vehicle.springbootvuejs.dto.CarDTO;
import com.vehicle.springbootvuejs.exception.ResourceNotFoundException;
import com.vehicle.springbootvuejs.mapper.CarMapper;
import com.vehicle.springbootvuejs.model.Car;
import com.vehicle.springbootvuejs.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
	private final CarRepository carRepository;
	private final CarMapper carMapper;

	public CarService(CarRepository carRepository, CarMapper carMapper) {
		this.carRepository = carRepository;
		this.carMapper = carMapper;
	}

	public CarDTO getCarByName(String name) {
		Car car = carRepository.findByName(name);
		if (car == null) {
			throw new ResourceNotFoundException("Car not found with name: " + name);
		}
		return carMapper.toDTO(car);
	}

	public CarDTO addCar(CarDTO carDTO) {
		try {
			Car car = carMapper.toEntity(carDTO);
			Car savedCar = carRepository.save(car);
			return carMapper.toDTO(savedCar);
		} catch (Exception e) {
			throw new RuntimeException("Failed to add car: " + e.getMessage());
		}
	}

	public List<CarDTO> getCarsByWheelCount(int wheelCount) {
		try {
			List<Car> cars = carRepository.findByWheelCount(wheelCount);
			if (cars.isEmpty()) {
				throw new ResourceNotFoundException("No cars found with wheel count: " + wheelCount);
			}
			return cars.stream().map(carMapper::toDTO).toList();
		} catch (Exception e) {
			throw new RuntimeException("Failed to fetch cars by wheel count: " + e.getMessage());
		}
	}

	public List<CarDTO> getAllCars() {
		try {
			List<Car> cars = carRepository.findAll();
			if (cars.isEmpty()) {
				throw new ResourceNotFoundException("No cars found.");
			}
			return cars.stream().map(carMapper::toDTO).toList();
		} catch (Exception e) {
			throw new RuntimeException("Failed to fetch all cars: " + e.getMessage());
		}
	}
}