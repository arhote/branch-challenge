package my.example.service.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidUsernameException.class)
    ResponseEntity<Object> handleInvalidUsername(InvalidUsernameException ex){
        String message = "<p>I'm sorry that username is either invalid or was not found.</p>" +
                        "<p>Please ensure that your username doesn't:" +
                        "<ul><li>Begin or end with a '-'.</li>" +
                        "<li>Contain more than 39 characters.</li>" +
                        "<li>Have any '--''s in it.</li>" +
                        "<li>Contain anything other than alphanumeric characters, '_' or '-'</li></ul></p>";

        return ResponseEntity.badRequest().body(message);
    }
}
