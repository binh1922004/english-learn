package learn.ELP.controller;

import learn.ELP.dto.ApiRespone;
import learn.ELP.dto.request.OtpRequest;
import learn.ELP.dto.request.OtpValidateRequest;
import learn.ELP.service.OtpService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/otp")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpController {
    OtpService otpService;
    
    @GetMapping("/get")
    public ApiRespone<Boolean> getOtp(@RequestBody OtpRequest otpRequest) {
        return ApiRespone.<Boolean>builder()
                .result(otpService.sendOtp(otpRequest))
                .build();
    }
    
    @PostMapping("/verify")
    public ApiRespone<Boolean> verifyOtp(@RequestBody OtpValidateRequest otpValidateRequest) {
        return ApiRespone.<Boolean>builder()
                .result(otpService.validateOtp(otpValidateRequest))
                .build();
    }
}
