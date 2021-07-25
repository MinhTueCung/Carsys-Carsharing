package com.carsys.carsharing.persistanceLayer.model.Transformer;

import com.carsys.carsharing.persistanceLayer.model.Address;

import com.carsys.carsharing.service.service.model.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public class AddressTransformer {


    public Address fromDtoToModel(AddressDTO addressDTO) {
        Address address = new Address();

        address.setPostcode(Integer.parseInt(addressDTO.getPostcode()));
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        address.setHouseno(addressDTO.getHouseno());

        return address;
    }

    public AddressDTO fromModelToDto(Address address) {
        AddressDTO addressDTO = new AddressDTO();

        addressDTO.setCity(address.getCity());
        addressDTO.setHouseno(address.getHouseno());
        addressDTO.setId(address.getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setPostcode(String.valueOf(address.getPostcode()));

        return addressDTO;
    }

}



