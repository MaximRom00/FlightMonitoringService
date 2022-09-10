package by.rom.flightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class FlightServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightServiceApplication.class, args);
    }

}
