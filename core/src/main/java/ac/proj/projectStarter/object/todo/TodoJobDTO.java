package ac.proj.projectStarter.object.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TodoJobDTO {
    String jobSummary;
    //String JobDescription;
    LocalDateTime deadLineDate;
    //JobStatus status; // TODO : find ways to map to Enum
    Integer status;
    String categoryName;

}
