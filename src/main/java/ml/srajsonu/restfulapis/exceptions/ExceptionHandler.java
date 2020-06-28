package ml.srajsonu.restfulapis.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {

        String errorMessageDescription = e.getLocalizedMessage();
        if (errorMessageDescription == null) errorMessageDescription = e.toString();

        CustomExceptionHandler exceptionHandler = new CustomExceptionHandler(new Date(), errorMessageDescription);

        return new ResponseEntity<>(
                exceptionHandler, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
    public ResponseEntity<Object> handleSpecificException(Exception e, WebRequest request) {

        String errorMessageDescription = e.getLocalizedMessage();
        if (errorMessageDescription == null) errorMessageDescription = e.toString();

        CustomExceptionHandler exceptionHandler = new CustomExceptionHandler(new Date(), errorMessageDescription);

        return new ResponseEntity<>(
                exceptionHandler, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

//    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NullPointerException.class})
//    public ResponseEntity<Object> handleNullPointerException(NullPointerException e, WebRequest request) {
//
//        String errorMessageDescription = e.getLocalizedMessage();
//        if (errorMessageDescription == null) errorMessageDescription = e.toString();
//
//        CustomExceptionHandler exceptionHandler = new CustomExceptionHandler(new Date(), errorMessageDescription);
//
//        return new ResponseEntity<>(
//                exceptionHandler, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
//        );
//    }
//
//    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserServiceException.class})
//    public ResponseEntity<Object> handleUserServiceException(UserServiceException e, WebRequest request) {
//
//        String errorMessageDescription = e.getLocalizedMessage();
//        if (errorMessageDescription == null) errorMessageDescription = e.toString();
//
//        CustomExceptionHandler exceptionHandler = new CustomExceptionHandler(new Date(), errorMessageDescription);
//
//        return new ResponseEntity<>(
//                exceptionHandler, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
//        );
//    }
}
