package ariticle.resource;

public class InvaildErrorResource {

    private String message;
    private Object errors;

    public InvaildErrorResource(String message, Object errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public Object getError() {
        return errors;
    }
}
