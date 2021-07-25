package com.carsys.carsharing.buisnessLayer;

import com.carsys.carsharing.basicauth.CustomAuthenticationProvider;
import com.carsys.carsharing.persistanceLayer.model.*;
import com.carsys.carsharing.persistanceLayer.model.Transformer.*;
import com.carsys.carsharing.persistanceLayer.repository.*;
import com.carsys.carsharing.service.service.boundary.MemberApi;
import com.carsys.carsharing.service.service.model.MemberDTO;
import com.carsys.carsharing.service.service.model.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;


@Component
@Controller
@RequestMapping("/v1")
public class MemberApiImpl implements MemberApi {

    private final MemberRepository memberRepository;
    private final MemberTransformer memberTransformer;
    private final LoginTransformer loginTransformer;
    private final LoginRepository loginRepository;
    private final AddressRepository addressRepository;
    private final AddressTransformer addressTransformer;
    private final RFIDRepository rfidRepository;
    private final RFIDTransformer rfidTransformer;
    private final TariffRepository tariffRepository;
    private final TariffTransformer tariffTransformer;

    @Autowired
    public MemberApiImpl(MemberRepository memberRepository, MemberTransformer memberTransformer, LoginTransformer loginTransformer, LoginRepository loginRepository
            ,AddressRepository addressRepository, AddressTransformer addressTransformer,RFIDRepository rfidRepository, RFIDTransformer rfidTransformer, TariffRepository tariffRepository,
                         TariffTransformer tariffTransformer) {
        this.memberRepository = Objects.requireNonNull(memberRepository);
        this.memberTransformer = Objects.requireNonNull(memberTransformer);

        this.loginTransformer = Objects.requireNonNull(loginTransformer);
        this.loginRepository = Objects.requireNonNull(loginRepository);

        this.addressRepository = Objects.requireNonNull(addressRepository);
        this.addressTransformer = Objects.requireNonNull(addressTransformer);


        this.rfidRepository = Objects.requireNonNull(rfidRepository);
        this.rfidTransformer = Objects.requireNonNull(rfidTransformer);

        this.tariffRepository = tariffRepository;
        this.tariffTransformer = tariffTransformer;
    }





    @Override
    public ResponseEntity<MemberDTO> addMember(@Valid MemberDTO memberDTO) {

        List<RFID> allRFIDs = rfidRepository.findAll();

        memberDTO.setActive(true);
        memberDTO.setUserStatus(StatusDTO.ACTIVE);
        memberDTO.setRfid(rfidTransformer.fromModelToDto(allRFIDs.get(1)));
        memberDTO.setTariff(tariffTransformer.fromModelToDto(tariffRepository.findByIdentifier("Standard")));
        Login login = loginTransformer.fromDtoToModel(memberDTO.getLogin());
        Member member = memberTransformer.fromDtoToModel(memberDTO);
        Address address = addressTransformer.fromDtoToModel(memberDTO.getAddress());


        if(DAYS.between(LocalDate.now(),LocalDate.parse(member.getDateOfBirth()))>=1) //zukunft
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "datum liegt in zukunft");
        }
        else if(DAYS.between(LocalDate.now(),LocalDate.parse(member.getDateOfBirth()))>(-6575)) //jünger als 18
        {
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "zu jung");
        }
        else if(login.getUsername() == "" || login.getEmail() == "" || login.getPassword() == "" || address.getCity() == "" || address.getHouseno() == "" || address.getCity() == "" || member.getDateOfBirth() == "" || member.getIban() == "" || member.getFirstName() == "" || member.getLastName() == "" || member.getPhone() == "" || member.getDrivingLicenseNumber() =="")
        {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "leere felder");
        }
        else if(login.getUsername() == null || login.getEmail() == null || login.getPassword() == null || address.getCity() == null || address.getHouseno() == null || address.getCity() == null || member.getDateOfBirth() == null || member.getIban() == null || member.getFirstName() == null || member.getLastName() == null || member.getPhone() == null || member.getDrivingLicenseNumber() == null)
        {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "leere felder");
        }
        else if(loginRepository.findByUsername(login.getUsername()) != null)
        {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "nutzername schon vergeben!");
        }
        else if(loginRepository.findByEmail(login.getEmail()) != null)
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "email schon vergeben!");
        }

        addressRepository.saveAndFlush(address);
        member.setLogin(login);
        member.setAddress(address);
        memberRepository.saveAndFlush(member);

        return ResponseEntity.ok(memberTransformer.fromModelToDto(member));
    }

    @Override
    public ResponseEntity<Void> deactivateMember(UUID memberId) {
        return null;
    }


    @Override
    public ResponseEntity<List<MemberDTO>> getAllMembers()
    {
        String roleOfCurrentUser = CustomAuthenticationProvider.getAuthenticationOfTheMemberThatIsLoggedIn().getAuthorities().toArray()[0].toString();
        Login loginOfCurrentUser = loginRepository.findByUsername(CustomAuthenticationProvider.getNameOfTheMemberThatIsLoggedIn());
        Optional<Member> member = memberRepository.findByLoginId(loginOfCurrentUser.getId());

        List<MemberDTO> memberList = new ArrayList<>();
        if(roleOfCurrentUser.equals("ROLE_USER"))
        {
            memberList.add(memberTransformer.fromModelToDto(member.get()));
        }
        else
        {
            //FutureDevelopment
        }

        return ResponseEntity.ok(memberList);
    }


    @Override
    public ResponseEntity<MemberDTO> getMemberById(UUID memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()){
            return ResponseEntity.ok(memberTransformer.fromModelToDto(member.get()));
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "member nicht gefunden");
    }


    @Override
    public ResponseEntity<MemberDTO> patchMember(@Valid MemberDTO memberDTO)
    {
        if(memberDTO.getId() == null || memberDTO.getId().equals(""))
        {
            return null;
        }

        Login login = loginRepository.findByUsername(CustomAuthenticationProvider.getNameOfTheMemberThatIsLoggedIn());
        Member member = memberRepository.findByLoginId(login.getId()).get();
        Optional<Address> address = addressRepository.findById(member.getAddress().getId());

        if(memberDTO.getFirstName() != null && !memberDTO.getFirstName().equals(""))
        {
            member.setFirstName(memberDTO.getFirstName());
        }
        if(memberDTO.getLastName() != null && !memberDTO.getLastName().equals(""))
        {
            member.setLastName(memberDTO.getLastName());
        }
        if(memberDTO.getPhone() != null && !memberDTO.getPhone().equals(""))
        {
            member.setPhone(memberDTO.getPhone());
        }
        if(memberDTO.getLogin() != null && !memberDTO.getLogin().equals(""))
        {
            if (memberDTO.getLogin().getEmail() != null && !memberDTO.getLogin().getEmail().equals("") && (loginRepository.findByEmail(memberDTO.getLogin().getEmail()) == null)) {
                member.getLogin().setEmail(memberDTO.getLogin().getEmail());
            }
            if (memberDTO.getLogin().getPassword() != null && !memberDTO.getLogin().getPassword().equals("")) {
                member.getLogin().setPassword(memberDTO.getLogin().getPassword());
            }
        }
        if(address.isPresent() && memberDTO.getAddress() != null) {
            if (memberDTO.getAddress().getStreet() != null && !memberDTO.getAddress().getStreet().equals("")) {
                address.get().setStreet(memberDTO.getAddress().getStreet());
            }
            if (memberDTO.getAddress().getHouseno() != null && !memberDTO.getAddress().getHouseno().equals("")) {
                address.get().setHouseno(memberDTO.getAddress().getHouseno());
            }
            if (memberDTO.getAddress().getPostcode() != null && !memberDTO.getAddress().getPostcode().equals("")) {
                address.get().setPostcode(Integer.parseInt(memberDTO.getAddress().getPostcode()));
            }
            if (memberDTO.getAddress().getCity() != null && !memberDTO.getAddress().getCity().equals("")) {
                address.get().setCity(memberDTO.getAddress().getCity());
            }
        }
        if(memberDTO.getIban() != null && !memberDTO.getIban().equals(""))
        {
            member.setIban(memberDTO.getIban());
        }
        if(memberDTO.getDrivingLicenseNumber() != null && !memberDTO.getDrivingLicenseNumber().equals(""))
        {
            member.setDrivingLicenseNumber(memberDTO.getDrivingLicenseNumber());
        }

        Address newAddress = addressRepository.saveAndFlush(address.get());
        member.setAddress(newAddress);
        Member newerMember = memberRepository.saveAndFlush(member);

        return ResponseEntity.ok(memberTransformer.fromModelToDto(newerMember));
    }


    @Override
    public ResponseEntity<MemberDTO> validateMember(@Valid MemberDTO memberDTO) {
        return null;
    }

}