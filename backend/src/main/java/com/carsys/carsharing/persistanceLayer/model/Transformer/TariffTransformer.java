package com.carsys.carsharing.persistanceLayer.model.Transformer;
import com.carsys.carsharing.persistanceLayer.model.Tariff;
import com.carsys.carsharing.service.service.model.TariffDTO;
import org.springframework.stereotype.Component;

@Component
public class TariffTransformer
{

    public TariffDTO fromModelToDto(Tariff tariff){
        TariffDTO tariffDTO = new TariffDTO();
        tariffDTO.setBezeichner(tariff.getIdentifier());
        tariffDTO.setProzentsatz(tariff.getPercentage());
        tariffDTO.setId(tariff.getId());

        return tariffDTO;
    }


    public Tariff fromDtoToModel(TariffDTO tariffDTO){
        Tariff tariff = new Tariff();
        tariff.setIdentifier(tariffDTO.getBezeichner());
        tariff.setPercentage(tariffDTO.getProzentsatz());
        tariff.setId(tariffDTO.getId());
        return tariff;
    }
}

