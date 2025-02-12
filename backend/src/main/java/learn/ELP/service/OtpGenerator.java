package learn.ELP.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpGenerator {
    private static final int OTP_EXPIRY_MINUTES = 5;
    private static final int THROTTLE_EXPIRY_MINUTES = 2;
    private static final String OTP_PREFIX = "otp:";
    private static final String THROTTLE_KEY_PREFIX = "throttle:";

    RedisTemplate<String, String> redisTemplate;
    Random random = new Random();
    public String generateOtp(String email) {

        if (redisTemplate.hasKey(OTP_PREFIX + email)) {
            throw new RuntimeException("Bạn phải chờ 30 giây trước khi gửi OTP mới!");
        }

        String otp = String.valueOf(100000 + random.nextInt(900000)); // 6-digit OTP
        String key = OTP_PREFIX + email;
        String throttle_ey = OTP_PREFIX + email;
        //save otp to redis
        redisTemplate.opsForValue().set(key, otp, OTP_EXPIRY_MINUTES, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(throttle_ey, "true", THROTTLE_EXPIRY_MINUTES, TimeUnit.MINUTES);
        return otp;
    }

    public boolean verifyOtp(String email, String otp) {
        String key = OTP_PREFIX + email;
        String storedOtp = redisTemplate.opsForValue().get(key);
        if (storedOtp != null && storedOtp.equals(otp)) {
            redisTemplate.delete(key);
            return true;
        }
        return false;
    }

}
