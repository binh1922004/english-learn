package learn.ELP.controller;

import com.nimbusds.jose.JOSEException;
import learn.ELP.dto.ApiRespone;
import learn.ELP.dto.request.AuthenticationRequest;
import learn.ELP.dto.respone.AuthenticationRespone;
import learn.ELP.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiRespone<AuthenticationRespone> login(@RequestBody AuthenticationRequest authenticationRequest) throws JOSEException {
        return ApiRespone.<AuthenticationRespone>builder()
                .result(authenticationService.authenticate(authenticationRequest))
                .build();
    }
}
