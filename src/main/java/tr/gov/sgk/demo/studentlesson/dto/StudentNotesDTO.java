package tr.gov.sgk.demo.studentlesson.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentNotesDTO {
    private Integer id;

    @NotNull(message = "Note cannot be null")
    @Min(value = 0, message = "Note must be greater than or equal to zero")
    @Max(value = 100, message = "Note must be less than or equal to 100")
    private Integer note;

    @NotNull(message = "Student cannot be null")
    private StudentDTO student;

    @NotNull(message = "Lesson cannot be null")
    private LessonDTO lesson;

    public void setStudentId(Integer studentId) {
        if (student == null) {
            student = new StudentDTO();
        }
        student.setId(studentId);
    }
    public void setLessonId(Integer lessonId) {
        if (lesson == null) {
            lesson = new LessonDTO();
        }
        lesson.setId(lessonId);
    }

}
