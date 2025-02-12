package learn.ELP.service;

import jakarta.mail.internet.MimeMessage;
import learn.ELP.dto.EmailDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    JavaMailSender javaMailSender;

    public Boolean sendSimpleMessage(EmailDTO emailDTO)
    {


        boolean isSent = false;
        try
        {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(String.join(",", emailDTO.getRecipients()));
            helper.setSubject(emailDTO.getSubject());
            helper.setText(emailDTO.getBody(), true);
            javaMailSender.send(message);
            isSent = true;
        }
        catch (Exception e) {
            log.error("Sending e-mail error: {}", e.getMessage());
        }
        return isSent;
    }

}
