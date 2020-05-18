package ariticle.exception;

import org.springframework.validation.Errors;

public class InvaildRequestException extends RuntimeException {

    private Errors errors;

    public InvaildRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
