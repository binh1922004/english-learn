package learn.ELP.controller;

import learn.ELP.dto.ApiRespone;
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
    public ApiRespone<String> getOtp(@RequestBody String username) {
        return ApiRespone.<String>builder()
                .result(otpService.generateOtp(username))
                .build();
    }
    
    @PostMapping("/verify")
    public ApiRespone<Boolean> verifyOtp(@RequestParam String username, @RequestParam String otp) {
        return ApiRespone.<Boolean>builder()
                .result(otpService.verifyOtp(username, otp))
                .build();
    }
}
