package by.rom.flightservice.controller;

import by.rom.flightservice.dto.PersonalTicketDto;
import by.rom.flightservice.dto.TicketDto;
import by.rom.flightservice.service.PersonalTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TicketController {

    private final PersonalTicketService personalTicketService;

    @PostMapping("/getTickets")
    public PersonalTicketDto getTickets(@RequestBody TicketDto tickerDto){
        return personalTicketService.getTickets(tickerDto);
    }

    @PostMapping("/getTicketsByDate")
    public PersonalTicketDto getTicketsByDate(@RequestBody TicketDto tickerDto){
        return personalTicketService.getTicketsByDate(tickerDto);
    }

    @GetMapping("/getPopularTicketsFromCity")
    public PersonalTicketDto getPopularTicketsFromCity(@RequestParam String origin, @RequestParam String currency){
        return personalTicketService.getPopularTicketsFromCity(origin, currency);
    }
}
