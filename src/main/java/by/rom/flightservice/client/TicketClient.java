package by.rom.flightservice.client;

import by.rom.flightservice.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "travel", url = "${travel.main.api}")
public interface TicketClient {
    @GetMapping("/prices_for_dates?currency=usd&sorting=price&direct=true&limit=1000&")
    ResponseDto getTickets(@RequestParam String origin,
                           @RequestParam String token);

    @GetMapping("/get_special_offers?locale=en&")
    ResponseDto getPopularTicketsFromCity(@RequestParam String origin,
                                          @RequestParam String token,
                                          @RequestParam String currency);
}
