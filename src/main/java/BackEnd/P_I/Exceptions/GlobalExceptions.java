package BackEnd.P_I.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptions {
   @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> procesarErrorBRE(BadRequestException ex){
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
   }
}
