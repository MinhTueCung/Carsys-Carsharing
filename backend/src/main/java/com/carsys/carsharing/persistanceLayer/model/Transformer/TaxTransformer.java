package com.carsys.carsharing.persistanceLayer.model.Transformer;

import com.carsys.carsharing.persistanceLayer.model.Tax;
import org.springframework.stereotype.Component;
import com.carsys.carsharing.service.service.model.TaxDTO;

@Component
public class TaxTransformer
{
    public TaxDTO fromModelToDto(Tax tax){
        TaxDTO taxDTO = TaxDTO.fromValue(tax.toString());
        return taxDTO;
    }
    public Tax fromDtoToModel(TaxDTO taxDTO){
        return Tax.valueOf(taxDTO.toString());
    }
}