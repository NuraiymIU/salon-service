package kg.megacom.salonservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

        @Autowired
        private JavaMailSender mailSender;

        public void sendMail(String to, String subject, String body)
        {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("nurainurbekova6@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        }

}
