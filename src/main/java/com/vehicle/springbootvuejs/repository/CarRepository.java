package com.vehicle.springbootvuejs.repository;

import com.vehicle.springbootvuejs.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

	Car findByName(String name);

	List<Car> findByWheelCount(int wheelCount);

}

