package by.rom.flightservice.dto;

import by.rom.flightservice.model.Currency;
import by.rom.flightservice.model.Ticket;
import lombok.Value;

import java.util.List;

@Value
public class PersonalTicketDto {
    List<Ticket> stocks;
    Currency currency;
}
