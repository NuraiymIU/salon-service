package kg.megacom.salonservice;

import kg.megacom.salonservice.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class SalonServiceApplication {

    @Autowired
    private EmailService emailService;

    public static void main(String[] args) {
        SpringApplication.run(SalonServiceApplication.class, args);
    }

/*    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){
        emailService.sendMail("cartoonchaka@gmail.com",
        "This is subject",
                "Ваш заказ принят!!!");


    }*/
}
