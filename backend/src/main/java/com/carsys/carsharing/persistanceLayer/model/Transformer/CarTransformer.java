package com.carsys.carsharing.persistanceLayer.model.Transformer;

import com.carsys.carsharing.persistanceLayer.model.Car;
import com.carsys.carsharing.service.service.model.CarDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CarTransformer  {

    @Autowired
    private ModelMapper modelMapper;

    public CarDTO fromModelToDTO(Car car){
        CarDTO carDto = modelMapper.map(car, CarDTO.class);
        return carDto;
    }

    public Car fromDTOtoModel(CarDTO carDTO){
        Car car = modelMapper.map(carDTO, Car.class);
        return car;
    }

}


