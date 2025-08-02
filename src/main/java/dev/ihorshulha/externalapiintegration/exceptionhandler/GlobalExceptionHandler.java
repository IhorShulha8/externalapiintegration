package dev.ihorshulha.externalapiintegration.exceptionhandler;

import dev.ihorshulha.externalapiintegration.dto.ErrorDto;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<ErrorDto> handleFeignItemNotFoundException(FeignException.NotFound ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                "Item not found in the external API",
                "EXTERNAL_API_ITEM_NOT_FOUND",
                request.getRequestURL().toString()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(ExternalApiUnauthorizedException.class)
    public ResponseEntity<ErrorDto> handleUnauthorizeException(ExternalApiUnauthorizedException ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                ex.getMessage(),
                "EXTERNAL_API_UNAUTHORIZED",
                request.getRequestURL().toString()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDto);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDto> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                "Endpoint not found",
                "EXTERNAL_API_NO_HANDLER_FOUND",
                request.getRequestURL().toString()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDto> handleBadRequestException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                "Invalid parameter" + ex.getName(),
                "EXTERNAL_API_BAD_REQUEST",
                request.getRequestURL().toString()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorDto errorDto = new ErrorDto(
                "Internal Server Error",
                "EXTERNAL_API_INTERNAL_SERVER_ERROR",
                request.getRequestURL().toString()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}
