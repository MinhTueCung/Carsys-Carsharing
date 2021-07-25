package com.carsys.carsharing.buisnessLayer;

import com.carsys.carsharing.persistanceLayer.model.Tariff;
import com.carsys.carsharing.persistanceLayer.model.Transformer.TariffTransformer;
import com.carsys.carsharing.persistanceLayer.repository.TariffRepository;
import com.carsys.carsharing.service.service.boundary.TariffApi;
import com.carsys.carsharing.service.service.model.TariffDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Component
@Controller
@RequestMapping("/v1")
public class TariffApilmpl implements TariffApi {

    private final TariffRepository tariffRepository;
    private final TariffTransformer tariffTransformer;

    @Autowired
    public TariffApilmpl(TariffRepository tariffRepository, TariffTransformer tariffTransformer ){
        this.tariffRepository = Objects.requireNonNull(tariffRepository);
        this.tariffTransformer = Objects.requireNonNull(tariffTransformer);
    }

    @Override
    public ResponseEntity<List<TariffDTO>> getAllTariffs() {
        List<Tariff> tariff = tariffRepository.findAll();
        return ResponseEntity.ok(tariff.stream().map(tariffTransformer::fromModelToDto).collect(Collectors.toList()));

    }

}


