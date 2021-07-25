package com.carsys.carsharing.buisnessLayer;

import com.carsys.carsharing.basicauth.CustomAuthenticationProvider;
import com.carsys.carsharing.service.service.boundary.LoginApi;
import com.carsys.carsharing.service.service.model.LoginStatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


@Component
@Controller
@RequestMapping("/v1")
public class LoginApilmpl implements LoginApi
{
  @Autowired
    public LoginApilmpl() {
    }

    @Override
    public ResponseEntity<LoginStatusDTO> loginid()
    {
        LoginStatusDTO dto = new LoginStatusDTO();

        if(CustomAuthenticationProvider.getAuthenticationOfTheMemberThatIsLoggedIn().getName().equals("anonymousUser"))
        {
            dto.setLoggedIn("false");
        }
        else
        {
            dto.setLoggedIn("true");
        }
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<Void> setloginid()
    {
        if(CustomAuthenticationProvider.getAuthenticationOfTheMemberThatIsLoggedIn().getName().equals("anonymousUser"))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nicht eingeloggt!");
        }

        throw new ResponseStatusException(HttpStatus.ACCEPTED, "eingeloggt!");
    }
}
