package learn.ELP.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpService {
    private static final int OTP_EXPIRY_MINUTES = 5;
    private static final String OTP_PREFIX = "otp:";

    RedisTemplate<String, String> redisTemplate;
    Random random = new Random();

    public String generateOtp(String userName) {

        String otp = String.valueOf(100000 + random.nextInt(900000)); // 6-digit OTP
        String key = OTP_PREFIX + userName;
        //save otp to redis
        redisTemplate.opsForValue().set(key, otp, OTP_EXPIRY_MINUTES, TimeUnit.MINUTES);

        return otp;
    }
}
