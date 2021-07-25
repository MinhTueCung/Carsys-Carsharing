package com.carsys.carsharing.persistanceLayer.model.Transformer;

import com.carsys.carsharing.persistanceLayer.model.Member;
import com.carsys.carsharing.service.service.model.MemberDTO;
import org.springframework.stereotype.Component;

@Component
public class MemberTransformer
{
    public MemberDTO fromModelToDto(Member member){
        LoginTransformer loginTransformer = new LoginTransformer();
        MemberDTO memberDTO = new MemberDTO();
        AddressTransformer addressTransformer = new AddressTransformer();
        RFIDTransformer rFIDTransformer = new RFIDTransformer();
        TariffTransformer tariffTransformer = new TariffTransformer();

        memberDTO.setLogin(loginTransformer.fromModelToDto(member.getLogin()));
        memberDTO.setFirstName(member.getFirstName());
        memberDTO.setLastName(member.getLastName());
        memberDTO.setId(member.getId());
        memberDTO.setPhone(member.getPhone());
        memberDTO.setIban(member.getIban());
        if (member.getDateOfBirth() != null)
        {
            memberDTO.setDateOfBirth(member.getDateOfBirth());
        }
        memberDTO.setDrivingLicenseNumber(member.getDrivingLicenseNumber());
        memberDTO.setAddress(addressTransformer.fromModelToDto(member.getAddress()));
        memberDTO.setRfid(rFIDTransformer.fromModelToDto(member.getRfid()));
        memberDTO.setTariff(tariffTransformer.fromModelToDto(member.getTariff()));
        memberDTO.setActive(member.isActive());
        memberDTO.getLogin().setUsername(member.getLogin().getUsername());

        return memberDTO;
    }

    public Member fromDtoToModel( MemberDTO memberDTO){
        Member member = new Member();
        LoginTransformer loginTransformer = new LoginTransformer();
        AddressTransformer addressTransformer = new AddressTransformer();
        RFIDTransformer rFIDTransformer = new RFIDTransformer();
        TariffTransformer tariffTransformer = new TariffTransformer();
        member.setIban(memberDTO.getIban());
        member.setDrivingLicenseNumber(memberDTO.getDrivingLicenseNumber());
        member.setLogin(loginTransformer.fromDtoToModel(memberDTO.getLogin()));
        member.setFirstName(memberDTO.getFirstName());
        member.setLastName(memberDTO.getLastName());
        member.setId(memberDTO.getId());
        if (memberDTO.getDateOfBirth() != null)
        {
            member.setDateOfBirth(memberDTO.getDateOfBirth().toString());
        }
        member.setPhone(memberDTO.getPhone());
        member.getLogin().setUsername(memberDTO.getLogin().getUsername());
        member.setAddress(addressTransformer.fromDtoToModel(memberDTO.getAddress()));
        member.setRfid(rFIDTransformer.fromDtoToModel(memberDTO.getRfid()));
        member.setTariff(tariffTransformer.fromDtoToModel(memberDTO.getTariff()));
        member.setActive(memberDTO.isActive());

        return member;
    }
}