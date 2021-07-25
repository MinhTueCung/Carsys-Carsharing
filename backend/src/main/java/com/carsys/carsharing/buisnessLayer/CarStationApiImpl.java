package com.carsys.carsharing.buisnessLayer;

import com.carsys.carsharing.persistanceLayer.model.Car;
import com.carsys.carsharing.persistanceLayer.model.CarStation;
import com.carsys.carsharing.persistanceLayer.model.Transformer.CarStationTransformer;
import com.carsys.carsharing.persistanceLayer.model.Transformer.CarTransformer;
import com.carsys.carsharing.persistanceLayer.repository.CarRepository;
import com.carsys.carsharing.persistanceLayer.repository.CarStationRepository;
import com.carsys.carsharing.service.service.boundary.CarstationApi;
import com.carsys.carsharing.service.service.model.CarDTO;
import com.carsys.carsharing.service.service.model.CarStationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Controller
@RequestMapping("/v1")
public class CarStationApiImpl implements CarstationApi {

    private final CarRepository carRepository;
    private final CarTransformer carTransformer;
    private final CarStationTransformer carStationTransformer;
    private final CarStationRepository carStationRepository;

    @Autowired
    public CarStationApiImpl(CarRepository carRepository, CarTransformer carTransformer, CarStationRepository carStationRepository, CarStationTransformer carStationTransformer){
        this.carRepository = Objects.requireNonNull(carRepository);
        this.carTransformer = Objects.requireNonNull(carTransformer);
        this.carStationRepository = Objects.requireNonNull(carStationRepository);
        this.carStationTransformer = Objects.requireNonNull(carStationTransformer);
    }


    @Override
    public ResponseEntity<CarDTO> addCarAssignToStation(UUID carstationId, @Valid CarDTO carDTO) {
        Optional<CarStation> carstationFromRepo = carStationRepository.findById(carstationId);

        Car car = carTransformer.fromDTOtoModel(carDTO);
        if(car.getLicensePlate() == null || car.getMaintenanceDate() == null || car.getPricePerHour() == 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"unvollständige Daten");
        }
        CarStation carstation;
        if(carstationFromRepo.isPresent()){
            carstation = carstationFromRepo.get();

        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "carstation nicht gefunden");
        }


        car.setStation(carstation);
        carRepository.save(car);

        // gibt ein carDTO zurück. Die ID davon wurde zugewieden und kann jetzt angezeigt werden
        return ResponseEntity.ok(carTransformer.fromModelToDTO(car));
    }

    @Override
    public ResponseEntity<CarStationDTO> addCarstation(@Valid CarStationDTO carstation) {
        CarStationDTO carStationDTO = new CarStationDTO();

        //noch keine funktion
        return ResponseEntity.ok(carStationDTO);
    }


    @Override
    public ResponseEntity<List<CarStationDTO>> getCarStations() {
        List<CarStationDTO> carStations = carStationRepository.findAll()
                                                                .stream()
                                                                .map(carStationTransformer::fromModelToDto)
                                                                .collect(Collectors.toList());
        return ResponseEntity.ok(carStations);
    }

    @Override
    public ResponseEntity<List<CarDTO>> getStationsCars(UUID carStationID) {
        List<Car> carsRepo = carRepository.findAllByCarstation_Id(carStationID);
        List<CarDTO> cars = new ArrayList<>();
        carsRepo.forEach(e -> cars.add(
                carTransformer.fromModelToDTO(e))
        );

        return ResponseEntity.ok(cars);
    }

}

