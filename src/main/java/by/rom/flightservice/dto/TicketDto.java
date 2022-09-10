package by.rom.flightservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@Value
public class TicketDto {

    String origin;

    String destination;

    @JsonProperty("origin_airport")
    String originAirport;

    @JsonProperty("destination_airport")
    String destinationAirport;

    Double price;

    @JsonProperty("flight_number")
    String flightNumber;

    @JsonProperty("departure_at")
    String departureAt;

    Integer transfers;

    Integer duration;
}
