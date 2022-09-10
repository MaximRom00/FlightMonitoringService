package by.rom.flightservice.exception;

public class LimitRequestsException extends RuntimeException{
    public LimitRequestsException(String message) {
        super(message);
    }
}
