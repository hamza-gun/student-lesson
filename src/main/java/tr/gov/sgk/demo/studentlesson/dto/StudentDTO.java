package tr.gov.sgk.demo.studentlesson.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDTO {
    private int id;

    @NotBlank(message = "First name cannot be blanked")
    private String firstName;

    @NotBlank(message = "Last name cannot be blanked")
    private String lastName;

    @NotNull(message = "Number cannot be null")
    @Min(value = 0, message = "Number must be greater than zero")
    private Integer number;
}
