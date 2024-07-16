package com.vehicle.springbootvuejs.controller;

import com.vehicle.springbootvuejs.constant.InternalConstant;
import com.vehicle.springbootvuejs.dto.CarDTO;
import com.vehicle.springbootvuejs.exception.ResourceNotFoundException;
import com.vehicle.springbootvuejs.response.ApiResponse;
import com.vehicle.springbootvuejs.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "http://localhost:5173", allowedHeaders = "*", allowCredentials = "true")
public class CarController {
	private final CarService carService;

	public CarController(CarService carService) {
		this.carService = carService;
	}

	@GetMapping("/{name}")
	public ResponseEntity<ApiResponse<CarDTO>> getCar(@PathVariable String name) {
		try {
			CarDTO carDTO = carService.getCarByName(name);
			return ResponseEntity.ok(new ApiResponse<>(InternalConstant.SUC, InternalConstant.SUCCESS, carDTO));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse<>(InternalConstant.ERROR_NOT_FOUND, ex.getMessage(), null));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(InternalConstant.SOMETHING_WENT_WRONG, "Something went wrong.", null));
		}
	}

	@PostMapping
	public ResponseEntity<ApiResponse<CarDTO>> addCar(@RequestBody CarDTO carDTO) {
		try {
			CarDTO addedCarDTO = carService.addCar(carDTO);
			return ResponseEntity.ok(new ApiResponse<>(InternalConstant.SUC, "Successfully added car.", addedCarDTO));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(InternalConstant.SOMETHING_WENT_WRONG, "Failed to add car: " + ex.getMessage(), null));
		}
	}

	@GetMapping("/wheelCount/{wheelCount}")
	public ResponseEntity<ApiResponse<List<CarDTO>>> getCarsByWheelCount(@PathVariable int wheelCount) {
		try {
			List<CarDTO> cars = carService.getCarsByWheelCount(wheelCount);
			return ResponseEntity.ok(new ApiResponse<>(InternalConstant.SUC, InternalConstant.SUCCESS, cars));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse<>(InternalConstant.ERROR_NOT_FOUND, ex.getMessage(), null));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(InternalConstant.SOMETHING_WENT_WRONG, "Failed to fetch cars by wheel count: " + ex.getMessage(), null));
		}
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<CarDTO>>> getAllCars() {
		try {
			List<CarDTO> cars = carService.getAllCars();
			return ResponseEntity.ok(new ApiResponse<>(InternalConstant.SUC, InternalConstant.SUCCESS, cars));
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ApiResponse<>(InternalConstant.ERROR_NOT_FOUND, ex.getMessage(), null));
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse<>(InternalConstant.SOMETHING_WENT_WRONG, "Failed to fetch all cars: " + ex.getMessage(), null));
		}
	}
}