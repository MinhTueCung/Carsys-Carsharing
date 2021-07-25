package com.carsys.carsharing.persistanceLayer.model.Transformer;

import com.carsys.carsharing.persistanceLayer.model.Login;
import com.carsys.carsharing.service.service.model.LoginDTO;
import org.springframework.stereotype.Component;

@Component
public class LoginTransformer {
    public LoginDTO fromModelToDto(Login login){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail(login.getEmail());
        login.setUsername(login.getUsername());
        login.setPassword(login.getPassword());
        return loginDTO;
    }
    public Login fromDtoToModel(LoginDTO loginDTO){
        Login login = new Login();
        login.setUsername(loginDTO.getUsername());
        login.setPassword(loginDTO.getPassword());
        login.setEmail(loginDTO.getEmail());
        return login;
    }
}

