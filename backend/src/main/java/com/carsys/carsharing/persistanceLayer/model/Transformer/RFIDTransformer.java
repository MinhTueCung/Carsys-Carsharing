package com.carsys.carsharing.persistanceLayer.model.Transformer;


import com.carsys.carsharing.persistanceLayer.model.RFID;
import org.springframework.stereotype.Component;
import com.carsys.carsharing.service.service.model.RFIDDTO;

@Component
public class RFIDTransformer
{
    public RFIDDTO fromModelToDto(RFID rfid){
        RFIDDTO rfiddto = new RFIDDTO();
        rfiddto.setId(rfid.getId());
        rfiddto.setChipNumber(rfid.getChipNumber());
        return rfiddto;
    }
    public RFID fromDtoToModel(RFIDDTO RFIDDTO){
        RFID rfid = new RFID();
        rfid.setId(RFIDDTO.getId());
        rfid.setChipNumber(RFIDDTO.getChipNumber());
        return rfid;
    }
}