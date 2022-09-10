package by.rom.flightservice.model;

import lombok.Builder;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@Builder
public class Ticket {

    String origin;
    String originAirport;
    String destination;
    String destinationAirport;
    Double price;
    String flightNumber;
    ZonedDateTime departureAt;
    Integer transfers;
    Integer duration;
}
