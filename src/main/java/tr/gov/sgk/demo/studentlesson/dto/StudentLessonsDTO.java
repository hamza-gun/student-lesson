package tr.gov.sgk.demo.studentlesson.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class StudentLessonsDTO {
    private int id;

    @NotNull(message = "Student Id cannot be null")
    private Integer studentId;

    @NotNull(message = "Lesson Id cannot be null")
    private List<Integer> lessonIds;

    public List<Integer> getLessonIds() {
        return lessonIds;
    }

    public void setLessonIds(List<Integer> lessonIds) {
        this.lessonIds = lessonIds;
    }
}
