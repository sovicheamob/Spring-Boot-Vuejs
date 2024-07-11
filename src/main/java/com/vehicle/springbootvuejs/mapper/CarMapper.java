package com.vehicle.springbootvuejs.mapper;

import com.vehicle.springbootvuejs.dto.CarDTO;
import com.vehicle.springbootvuejs.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
	public CarDTO toDTO(Car car) {
		CarDTO dto = new CarDTO();
		dto.setName(car.getName());
		dto.setWheelCount(car.getWheelCount());
		return dto;
	}

	public Car toEntity(CarDTO dto) {
		Car car = new Car();
		car.setName(dto.getName());
		car.setWheelCount(dto.getWheelCount());
		return car;
	}
}
