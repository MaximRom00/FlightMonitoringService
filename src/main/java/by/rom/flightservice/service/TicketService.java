package by.rom.flightservice.service;

import by.rom.flightservice.client.TicketClient;
import by.rom.flightservice.dto.ResponseDto;
import by.rom.flightservice.dto.TicketDto;
import by.rom.flightservice.exception.LimitRequestsException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketService {

    private final TicketClient bondClient;

    @Cacheable(value = "cacheTicket")
    public ResponseDto getAllTickets(TicketDto ticketDto) {
        ResponseDto tickets = bondClient.getTickets(ticketDto.getOrigin(), System.getenv("API_TOKEN"));

        if (tickets == null){
            throw new LimitRequestsException("Aviasales isn't answering, number of requests exceeded.");
        }
        return tickets;
    }

    @Cacheable(value = "cacheCity")
    public ResponseDto getTicketsFromCity(String origin, String currency){
        ResponseDto tickets = bondClient.getPopularTicketsFromCity(origin, System.getenv("API_TOKEN"), currency);

        System.out.println(tickets);
        if (tickets == null){
            throw new LimitRequestsException("Aviasales isn't answering, number of requests exceeded.");
        }
        return tickets;
    }
}
