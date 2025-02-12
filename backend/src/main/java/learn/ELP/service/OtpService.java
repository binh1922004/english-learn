package learn.ELP.service;

import learn.ELP.dto.EmailDTO;
import learn.ELP.dto.request.OtpRequest;
import learn.ELP.dto.request.OtpValidateRequest;
import learn.ELP.entity.Users;
import learn.ELP.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpService {
    OtpGenerator otpGenerator;
    EmailService emailService;
    UsersRepository usersRepository;

    public Boolean sendOtp(OtpRequest request) {
        var email = request.getEmail();
        String otp = otpGenerator.generateOtp(email);
        String htmlContent = "<div style='text-align: center; font-family: Arial, sans-serif;'>" +
                "<h2 style='color: #333;'>Mã OTP của bạn</h2>" +
                "<p style='font-size: 20px; font-weight: bold; color: #007bff;'>" + otp + "</p>" +
                "<p>Vui lòng không chia sẻ mã này với bất kỳ ai.</p>" +
                "<br/>" +
                "<p style='font-size: 14px; color: #888;'>Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!</p>" +
                "</div>";
        List<String> recipients = new ArrayList<>();
        recipients.add(email);
        EmailDTO emailDTO = EmailDTO.builder()
                .recipients(recipients)
                .body(htmlContent)
                .subject("Spring Boot OTP Password.")
                .build();
        return emailService.sendSimpleMessage(emailDTO);
    }

    public Boolean validateOtp(OtpValidateRequest request) {
        var email = request.getEmail();
        var otp = request.getOtp();
        return otpGenerator.verifyOtp(email, otp);
    }
}
