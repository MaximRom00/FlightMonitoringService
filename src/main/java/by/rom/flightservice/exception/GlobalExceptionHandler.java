package by.rom.flightservice.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handler(RuntimeException ex, WebRequest request){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = ErrorResponse.builder()
                .message(ex.getMessage())
                .httpStatus(httpStatus)
                .path(request.getDescription(false))
                .timestamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>("Invalid format: " + ex.getMessage() + ".\nStatus: "+ status, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> bodyError = new LinkedHashMap<>();
        bodyError.put("timestamp", LocalDateTime.now());
        bodyError.put("status", status.value());


        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        bodyError.put("errors", errors);

        return new ResponseEntity<>(bodyError, status);

    }
}
