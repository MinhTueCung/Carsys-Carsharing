package com.carsys.carsharing.persistanceLayer.model.Transformer;

import com.carsys.carsharing.persistanceLayer.model.Car;
import com.carsys.carsharing.persistanceLayer.model.CarStation;
import com.carsys.carsharing.service.service.model.CarDTO;
import com.carsys.carsharing.service.service.model.CarStationDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarStationTransformer {

    public CarStationDTO fromModelToDto(CarStation carstation) {
        CarStationDTO carStationDTO = new CarStationDTO();
        AddressTransformer addressTransformer = new AddressTransformer();

        carStationDTO.setId(carstation.getId());
        carStationDTO.setAddress(addressTransformer.fromModelToDto(carstation.getAddress()));
        return carStationDTO;
    }

    public CarStation fromDtoToModel(CarStationDTO carStationDTO) {
        CarStation carstation = new CarStation();
        AddressTransformer addressTransformer = new AddressTransformer();

        carstation.setId(carStationDTO.getId());
        carstation.setAddress(addressTransformer.fromDtoToModel(carStationDTO.getAddress()));
        return carstation;
    }

    private List<CarDTO> fromCarToCarDtoList(List<Car> cars) {
        CarTransformer carTransformer = new CarTransformer();
        return cars.stream().map(carTransformer::fromModelToDTO).collect(Collectors.toList());
    }

    private List<Car> fromDtoToCarList(List<CarDTO> cars) {
        CarTransformer carTransformer = new CarTransformer();
        return cars.stream().map(carTransformer::fromDTOtoModel).collect(Collectors.toList());
    }
}

