package fils.licenta.ioana.advisor;

import fils.licenta.ioana.exception.EntityAlreadyExistsException;
import fils.licenta.ioana.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    public ResponseEntity<HashMap> entityAlreadyExistsExceptionHandler(final EntityAlreadyExistsException exception) {
        HashMap<String, String> exceptionBody = new HashMap<>();
        exceptionBody.put("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionBody);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<HashMap> entityNotFoundExceptionHandler(final EntityNotFoundException exception) {
        HashMap<String, String> exceptionBody = new HashMap<>();
        exceptionBody.put("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionBody);
    }
}
