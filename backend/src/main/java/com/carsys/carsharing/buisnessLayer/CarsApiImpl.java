package com.carsys.carsharing.buisnessLayer;

import com.carsys.carsharing.persistanceLayer.model.Car;
import com.carsys.carsharing.persistanceLayer.model.Transformer.CarTransformer;
import com.carsys.carsharing.persistanceLayer.repository.CarRepository;
import com.carsys.carsharing.service.service.boundary.CarsApi;
import com.carsys.carsharing.service.service.model.CarDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Component
@RequestMapping("/v1")
public class CarsApiImpl implements CarsApi {

    private CarTransformer carTransformer;
    private CarRepository carRepository;

    public CarsApiImpl(CarTransformer carTransformer, CarRepository carRepository) {
        this.carTransformer = carTransformer;
        this.carRepository = carRepository;
    }

    @Override
    public ResponseEntity<List<CarDTO>> getAllCars(){
        List<Car> list_of_all_cars = this.carRepository.findAll();
        List<CarDTO> list_of_all_cars_in_DTO = new ArrayList<>();
        for(Car a_car : list_of_all_cars){
            if(a_car.isActive()) {
                CarDTO a_car_dto = this.carTransformer.fromModelToDTO(a_car);
                String zugehoerige_station_stadtname = a_car.getStation().getAddress().getCity();
                a_car_dto.setCarstationCityName(zugehoerige_station_stadtname);
                list_of_all_cars_in_DTO.add(a_car_dto);
            }
        }
        return ResponseEntity.ok(list_of_all_cars_in_DTO);
    }

    @Override
    public ResponseEntity<CarDTO> getOneCar(UUID carID){
        Optional<Car> the_car_optional = this.carRepository.findById(carID);
        return ResponseEntity.ok(this.carTransformer.fromModelToDTO(the_car_optional.get()));
    }

    @Override
    public ResponseEntity<Void> postOneCar(CarDTO car){
        Car the_car = this.carTransformer.fromDTOtoModel(car);
        this.carRepository.save(the_car);
        return ResponseEntity.noContent().build();
    }

}
