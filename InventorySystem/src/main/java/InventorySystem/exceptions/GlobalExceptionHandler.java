package InventorySystem.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException e,HttpServletRequest request){
        Map<String,String> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("message",e.getMessage());
        error.put("path", request.getRequestURI());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(BadRequestException e,HttpServletRequest request){
        Map<String,String> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("message",e.getMessage());
        error.put("path", request.getRequestURI());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InventoryAlreadyExistsException.class)
    public ResponseEntity<?> handleInventoryAlreadyExists(InventoryAlreadyExistsException e, HttpServletRequest request){
        Map<String,String> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("message",e.getMessage());
        error.put("path", request.getRequestURI());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationError(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e, HttpServletRequest request){
        Map<String,String> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("message",e.getMessage());
        error.put("path", request.getRequestURI());

        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
