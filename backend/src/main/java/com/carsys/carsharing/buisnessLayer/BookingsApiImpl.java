package com.carsys.carsharing.buisnessLayer;

import com.carsys.carsharing.basicauth.CustomAuthenticationProvider;
import com.carsys.carsharing.persistanceLayer.model.*;
import com.carsys.carsharing.persistanceLayer.model.Transformer.BookingTransformer;
import com.carsys.carsharing.persistanceLayer.repository.BookingRepository;
import com.carsys.carsharing.persistanceLayer.repository.LoginRepository;
import com.carsys.carsharing.persistanceLayer.repository.UserRepository;
import com.carsys.carsharing.persistanceLayer.repository.MemberRepository;
import com.carsys.carsharing.persistanceLayer.repository.*;
import com.carsys.carsharing.rfid.RFIDClient;
import com.carsys.carsharing.service.service.boundary.BookingsApi;
import com.carsys.carsharing.service.service.model.BookingDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/v1")
@Component
public class BookingsApiImpl implements BookingsApi {

    private final BookingTransformer bookingTransformer;
    private final BookingRepository bookingRepository;
    private final LoginRepository loginRepository;
    private final UserRepository userRepository;
    private final MemberRepository memberRepository;
    private final CarRepository carRepository;

    public BookingsApiImpl(BookingTransformer bookingTransformer, MemberRepository memberRepository, LoginRepository loginRepository, UserRepository userRepository, BookingRepository bookingRepository, CarRepository carRepository ) {
        this.bookingTransformer = bookingTransformer;
        this.bookingRepository = bookingRepository;
        this.memberRepository = memberRepository;
        this.loginRepository = loginRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    @Override
    public ResponseEntity<BookingDTO> changeOneBookingById(UUID bookingID, BookingDTO booking) {
        Booking updated_booking = this.bookingTransformer.convertToEntity(booking);
        Booking old_booking = this.bookingRepository.findById(bookingID).get();
        old_booking.setCar(updated_booking.getCar());
        old_booking.setMember(updated_booking.getMember());
        old_booking.setStartDate(updated_booking.getStartDate());
        old_booking.setEndDate(updated_booking.getEndDate());
        old_booking.setBookingStatus(updated_booking.getBookingStatus());
        this.bookingRepository.save(old_booking);
        return ResponseEntity.ok(this.bookingTransformer.convertToDto(updated_booking));
    }

    @Override
    public ResponseEntity<List<BookingDTO>> deleteOneBookingById(UUID bookingID){
        List<BookingDTO> to_return_list = new ArrayList<>();
        Login login = loginRepository.findByUsername(CustomAuthenticationProvider.getNameOfTheMemberThatIsLoggedIn());
        Member member = memberRepository.findByLoginId(login.getId()).get();

        Optional<Booking> booking = bookingRepository.findById(bookingID);

        if(booking.isPresent())
        {
            if(booking.get().getBookingStatus().equals(BookingStatus.RESERVED));
            {
                booking.get().setBookingStatus(BookingStatus.CANCELED);
                bookingRepository.saveAndFlush(booking.get());
            }
        }

        List<Booking> bookings = bookingRepository.findAllByMember(member);
        ListIterator<Booking> myListIterator = bookings.listIterator();
        while (myListIterator.hasNext()) {
            to_return_list.add(bookingTransformer.convertToDto(myListIterator.next()));
        }
        return ResponseEntity.ok(to_return_list);
    }


    @Override
    public ResponseEntity<List<BookingDTO>> getAllBookings()
    {
        List<BookingDTO> list_of_all_bookings_DTO = new ArrayList<>();
        List<Booking> list_of_all_bookings = this.bookingRepository.findAll();
        for (Booking a_booking : list_of_all_bookings) {
            list_of_all_bookings_DTO.add(this.bookingTransformer.convertToDto(a_booking));
        }
        return ResponseEntity.ok(list_of_all_bookings_DTO);
    }


    @Override
    public ResponseEntity<List<BookingDTO>> getAllMemberBookings(){
        List<BookingDTO> to_return_list = new ArrayList<>();
        Login login = loginRepository.findByUsername(CustomAuthenticationProvider.getNameOfTheMemberThatIsLoggedIn());
        Member member = memberRepository.findByLoginId(login.getId()).get();

        List<Booking> bookings = bookingRepository.findAllByMember(member);

        ListIterator<Booking> myListIterator = bookings.listIterator();
        while (myListIterator.hasNext()) {
            to_return_list.add(bookingTransformer.convertToDto(myListIterator.next()));
        }

        return ResponseEntity.ok(to_return_list);
    }

    @Override
    public ResponseEntity<BookingDTO> getOneBookingById(UUID bookingID){
        Optional<Booking> the_booking_optional = this.bookingRepository.findById(bookingID);
        return ResponseEntity.ok(this.bookingTransformer.convertToDto(the_booking_optional.get()));
    }

    @Override
    public ResponseEntity<String> postOneBooking(BookingDTO booking){
        Login login = loginRepository.findByUsername(CustomAuthenticationProvider.getNameOfTheMemberThatIsLoggedIn());
        Optional<User> userOptional = userRepository.findByLoginId(login.getId());
        Member member = memberRepository.findByLoginId(login.getId()).get();

        if(userOptional.isPresent()){
            UUID the_current_User_UserID = userOptional.get().getId();
            booking.setMemberId(the_current_User_UserID);
            Booking the_booking = this.bookingTransformer.convertToEntity(booking);

            // Prüft, ob das Auto schon von einer anderen Buchung belegt ist
            String[] the_booking_startDate_Array = the_booking.getStartDate().split("\\.");
            String[] the_booking_startTime_Array = the_booking.getStartTime().split(":");
            String[] the_booking_endDate_Array = the_booking.getEndDate().split("\\.");
            String[] the_booking_endTime_Array = the_booking.getEndTime().split(":");
            int the_booking_Start_in_Sum_Minutes = Integer.parseInt(the_booking_startDate_Array[0]) * 24 * 60 + Integer.parseInt(the_booking_startDate_Array[1]) * 30 * 24 * 60
                    + Integer.parseInt(the_booking_startDate_Array[2]) * 365 * 24 * 60 + Integer.parseInt(the_booking_startTime_Array[0]) * 60 + Integer.parseInt(the_booking_startTime_Array[1]);
            int the_booking_End_in_Sum_Minutes = Integer.parseInt(the_booking_endDate_Array[0]) * 24 * 60 + Integer.parseInt(the_booking_endDate_Array[1]) * 30 * 24 * 60
                    + Integer.parseInt(the_booking_endDate_Array[2]) * 365 * 24 * 60 + Integer.parseInt(the_booking_endTime_Array[0]) * 60 + Integer.parseInt(the_booking_endTime_Array[1]);

            UUID carId = booking.getCarId();
            boolean thereIsCollision = false;
            List<Booking> collision_bookings_list = this.bookingRepository.findByCar_Id(carId);
            for(Booking collision_booking : collision_bookings_list){
                if(collision_booking.getBookingStatus().toString().equals("RESERVED") || collision_booking.getBookingStatus().toString().equals("RENTED")){
                    if(this.bookingCollisionOrNot(the_booking_Start_in_Sum_Minutes, the_booking_End_in_Sum_Minutes, collision_booking.getStartDate(), collision_booking.getStartTime(), collision_booking.getEndDate(), collision_booking.getEndTime())){
                        thereIsCollision = true;
                        break;
                    }
                }
            }
            if(thereIsCollision) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Das Auto ist momentan ausgeliehen und steht nicht zur Verfügung!");
            }

            // Prüft, ob dieses Mitglied schon zum selben Zeitraum ein anderes Auto mietet
            List<Booking> other_bookings_of_this_memeber = this.bookingRepository.findByMember_Id(the_current_User_UserID);
            boolean collision = false;
            if(!other_bookings_of_this_memeber.isEmpty()){
                // Wenn "collision_booking" den Status "COMPLETED" oder "CANCELED" --> Alles gut!
                // Wenn nicht, dann prüfen!
                for(Booking a_booking : other_bookings_of_this_memeber){
                    if(a_booking.getBookingStatus().toString().equals("RESERVED") || a_booking.getBookingStatus().toString().equals("RENTED")){
                        if(this.bookingCollisionOrNot(the_booking_Start_in_Sum_Minutes, the_booking_End_in_Sum_Minutes, a_booking.getStartDate(), a_booking.getStartTime(), a_booking.getEndDate(), a_booking.getEndTime())){
                            collision = true;
                            break;
                        }
                    }
                }
            }
            if(collision){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Sie haben bereits eine Buchung im selben Zeitraum abgeschlossen!");
            }

            this.bookingRepository.save(the_booking);


            Optional<Car> car = carRepository.findById(the_booking.getCar().getId());
            //TESTMETHODE, UM EIN RFID-OBJEKT ZU UEBERGEBEN
            if(car.isPresent() && car.get().getLicensePlate().equals("HB-CG-310")) // Testauto
            {
                StringBuilder endDateAndTime = new StringBuilder();
                StringBuilder startDateAndTime = new StringBuilder();
                endDateAndTime.append(booking.getEndDate()).append(" " + booking.getEndTime());
                startDateAndTime.append(booking.getStartDate()).append(" " + booking.getStartTime());

                DateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
                Date enddate = null;
                Date startdate = null;


                try {
                    enddate = format.parse(endDateAndTime.toString());
                    startdate = format.parse(startDateAndTime.toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                RFIDClient rfidThread = new RFIDClient(this, the_booking.getId(), startdate, enddate, member.getRfid().getChipNumber());
                rfidThread.start();
            }

            return ResponseEntity.status(HttpStatus.OK).body("Buchung erfolgreich!");
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mitglied existiert nicht!");
        }
    }

    private boolean bookingCollisionOrNot(int the_booking_Start_in_Sum_Minutes, int the_booking_End_in_Sum_Minutes, String collisionBookingStartDate, String collisionBookingStartTime, String collisionBookingEndDate, String collisionBookingEndTime){
        boolean b = false;
        String[] collision_booking_startDate_Array = collisionBookingStartDate.split("\\.");
        String[] collision_booking_startTime_Array = collisionBookingStartTime.split(":");
        String[] collision_booking_endDate_Array = collisionBookingEndDate.split("\\.");
        String[] collision_booking_endTime_Array = collisionBookingEndTime.split(":");
        int collision_booking_Start_in_Sum_Minutes = Integer.parseInt(collision_booking_startDate_Array[0]) * 24 * 60 + Integer.parseInt(collision_booking_startDate_Array[1]) * 30 * 24 * 60
                + Integer.parseInt(collision_booking_startDate_Array[2]) * 365 * 24 * 60 + Integer.parseInt(collision_booking_startTime_Array[0]) * 60 + Integer.parseInt(collision_booking_startTime_Array[1]);
        int collision_booking_End_in_Sum_Minutes = Integer.parseInt(collision_booking_endDate_Array[0]) * 24 * 60 + Integer.parseInt(collision_booking_endDate_Array[1]) * 30 * 24 * 60
                + Integer.parseInt(collision_booking_endDate_Array[2]) * 365 * 24 * 60 + Integer.parseInt(collision_booking_endTime_Array[0]) * 60 + Integer.parseInt(collision_booking_endTime_Array[1]);
        if ((collision_booking_Start_in_Sum_Minutes <= the_booking_Start_in_Sum_Minutes && the_booking_Start_in_Sum_Minutes <= collision_booking_End_in_Sum_Minutes) || (collision_booking_Start_in_Sum_Minutes <= the_booking_End_in_Sum_Minutes && the_booking_End_in_Sum_Minutes <= collision_booking_End_in_Sum_Minutes)) {
            // Kollision!!!
            b = true;
        }
        return b;
    }

    // TESTMETHODE, UM EIN AUTO VORZEITIG DURCH RFID ZURUECKZUGEBEN
    public void earlyReturnOfCarDueToRFID(UUID bookingID)
    {
        Optional<Booking> booking = bookingRepository.findById(bookingID);

        if(booking.isPresent())
        {
            booking.get().setBookingStatus(BookingStatus.COMPLETED);
            bookingRepository.saveAndFlush(booking.get());
        }
    }

    // TESTMETHODE, UM EIN AUTO DURCH RFID IN EMPFANGZUNEHMEN
    public void setBookingStatusDueToRFID(UUID bookingID)
    {
        Optional<Booking> booking = bookingRepository.findById(bookingID);

        if(booking.isPresent())
        {
            booking.get().setBookingStatus(BookingStatus.RENTED);
            bookingRepository.saveAndFlush(booking.get());
        }
    }
}


