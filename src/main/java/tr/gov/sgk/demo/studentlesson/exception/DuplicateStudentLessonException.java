package tr.gov.sgk.demo.studentlesson.exception;

public class DuplicateStudentLessonException extends RuntimeException{
    public DuplicateStudentLessonException(String message) {
        super(message);
    }
}
