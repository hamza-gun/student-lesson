package tr.gov.sgk.demo.studentlesson.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LessonDTO {
    private int id;

    @NotBlank(message = "Lesson name cannot be blanked")
    private String lessonName;

    @NotBlank(message = "Lesson code cannot be blanked")
    private String lessonCode;
}
