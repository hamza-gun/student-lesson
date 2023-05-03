package tr.gov.sgk.demo.studentlesson.exception;

public class InvalidFormException extends RuntimeException{
    public InvalidFormException(String message) {
        super(message);
    }
}
