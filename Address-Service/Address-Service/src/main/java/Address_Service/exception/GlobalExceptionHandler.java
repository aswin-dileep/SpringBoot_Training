package Address_Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotAvailableException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotAvailableException(ResourceNotAvailableException e, WebRequest request){
        Map<String,Object> error = new HashMap<>();
        error.put("TimeStamp", LocalDateTime.now());
        error.put("Status", HttpStatus.NOT_FOUND.value());
        error.put("Error","Not Found");
        error.put("Message",e.getMessage());
        error.put("Path",request.getDescription(false));

        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
