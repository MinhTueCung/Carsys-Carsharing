package com.carsys.carsharing.persistanceLayer.model.Transformer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.carsys.carsharing.service.service.model.BookingDTO;
import com.carsys.carsharing.persistanceLayer.model.Booking;
import com.carsys.carsharing.persistanceLayer.model.*;

@Component
public class BookingTransformer {

    @Autowired
    private ModelMapper modelMapper;

    public BookingDTO convertToDto(Booking booking) {
        BookingDTO bookingDTO = modelMapper.map(booking, BookingDTO.class);
        return bookingDTO;
    }

    public Booking convertToEntity(BookingDTO bookingDTO) {
        Booking booking = modelMapper.map(bookingDTO, Booking.class);
        return booking;
    }
}

