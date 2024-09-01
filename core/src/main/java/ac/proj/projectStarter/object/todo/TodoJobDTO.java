package ac.proj.projectStarter.object.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@ToString
public class TodoJobDTO {
    String jobSummary;
    LocalDate deadLineDate;
    Integer status;
    String categoryName;

    public TodoJobDTO(String jobSummary, LocalDate deadLineDate, Integer status, String categoryName) {
        this.jobSummary = jobSummary;
        this.deadLineDate = deadLineDate;
        this.status = status;
        this.categoryName = categoryName;
    }
}


