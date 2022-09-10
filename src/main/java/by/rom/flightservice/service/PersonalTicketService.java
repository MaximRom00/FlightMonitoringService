package by.rom.flightservice.service;

import by.rom.flightservice.dto.ResponseDto;
import by.rom.flightservice.exception.NotFoundException;
import by.rom.flightservice.model.Ticket;
import by.rom.flightservice.dto.PersonalTicketDto;
import by.rom.flightservice.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PersonalTicketService {

    private final TicketService ticketService;

    public PersonalTicketDto getTickets(TicketDto tickerDto) {
        ResponseDto responseDto = ticketService.getAllTickets(tickerDto);

        List<TicketDto> ticketsList;

        if (tickerDto.getDestination() != null){
            ticketsList = responseDto.getData().stream()
                    .filter(ticket-> tickerDto.getOrigin().equalsIgnoreCase(ticket.getOrigin()))
                    .filter(ticket-> tickerDto.getDestination().equalsIgnoreCase(ticket.getDestination()))
                    .collect(Collectors.toList());
        }
        else {
            ticketsList = responseDto.getData().stream()
                    .filter(ticket-> tickerDto.getOrigin().equalsIgnoreCase(ticket.getOrigin()))
                    .collect(Collectors.toList());
        }

        List<Ticket> myTickets = createMyTickets(ticketsList);

        if (myTickets.isEmpty()){
          throw new NotFoundException("Tickets weren't found, check your origin: "+ tickerDto.getOrigin() + " and destination: " + tickerDto.getDestination());
        }
        return new PersonalTicketDto(myTickets, responseDto.getCurrency());
    }

    public PersonalTicketDto getTicketsByDate(TicketDto tickerDto) {
        ResponseDto responseDto = ticketService.getAllTickets(tickerDto);

        List<TicketDto> ticketsList = responseDto.getData().stream()
                .filter(ticket-> tickerDto.getOrigin().equalsIgnoreCase(ticket.getOrigin()))
                .filter(ticket-> tickerDto.getDestination().equalsIgnoreCase(ticket.getDestination()))
                .filter(ticket-> tickerDto.getDepartureAt().contains(ticket.getDepartureAt().substring(0,10)))
                .collect(Collectors.toList());

        List<Ticket> myTickets = createMyTickets(ticketsList);

        if (myTickets.isEmpty()){
            throw new NotFoundException("Tickets weren't found, check your departure time: "+ tickerDto.getDepartureAt());
        }
        return new PersonalTicketDto(myTickets, responseDto.getCurrency());
    }

    public PersonalTicketDto getPopularTicketsFromCity(String origin, String currency) {
        ResponseDto responseDto = ticketService.getTicketsFromCity(origin, currency);

        List<Ticket> myTickets = createMyTickets(responseDto.getData());

        if (myTickets.isEmpty()){
            throw new NotFoundException("Tickets weren't found, check your origin: " + origin);
        }
        return new PersonalTicketDto(myTickets, responseDto.getCurrency());
    }

    private List<Ticket> createMyTickets(List<TicketDto> ticketsList) {
        return ticketsList.stream().map(t -> Ticket.builder()
                .origin(t.getOrigin())
                .originAirport(t.getOriginAirport())
                .destination(t.getDestination())
                .duration(t.getDuration())
                .departureAt(ZonedDateTime.parse(t.getDepartureAt()))
                .destinationAirport(t.getDestinationAirport())
                .flightNumber(t.getFlightNumber())
                .price(t.getPrice())
                .transfers(t.getTransfers())
                .build()).collect(Collectors.toList());
    }
}
