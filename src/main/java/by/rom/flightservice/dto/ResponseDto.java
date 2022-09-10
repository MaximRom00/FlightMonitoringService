package by.rom.flightservice.dto;

import by.rom.flightservice.model.Currency;
import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {
    private boolean success;
    private List<TicketDto> data;
    private Currency currency;
}
