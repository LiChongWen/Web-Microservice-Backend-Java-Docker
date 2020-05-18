package ariticle.handler;


import ariticle.exception.InvaildRequestException;
import ariticle.exception.NotFoundException;
import ariticle.resource.ErrorResource;
import ariticle.resource.FieldErrorResource;
import ariticle.resource.InvaildErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApiExceptionHandler {

    //Deal with Not Found Resource
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handlerNotFound(RuntimeException e) {
        System.out.println("here----");
        ErrorResource errorResource = new ErrorResource(e.getMessage());
        return new ResponseEntity<>(errorResource, HttpStatus.NOT_FOUND);
    }

    //Deal with Parameter invalid
    @ExceptionHandler(InvaildRequestException.class)
    @ResponseBody
    public ResponseEntity<?> handlerInvalidParameters(InvaildRequestException e) {
        Errors errors = e.getErrors();
        System.out.println("here----");
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<FieldErrorResource> fieldErrorResources = new ArrayList<>();
        for(FieldError fieldError: fieldErrors){
            FieldErrorResource fieldErrorResource = new FieldErrorResource(fieldError.getObjectName(),
                                                                            fieldError.getField(),
                                                                            fieldError.getCode(),
                                                                            fieldError.getDefaultMessage());
            fieldErrorResources.add(fieldErrorResource);
        }
        InvaildErrorResource errorResource = new InvaildErrorResource(e.getMessage(), fieldErrorResources);
        return new ResponseEntity<>(errorResource, HttpStatus.BAD_REQUEST);
    }

    //Deal with Global exception
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleException(Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
