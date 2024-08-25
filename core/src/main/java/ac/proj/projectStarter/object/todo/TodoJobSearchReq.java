package ac.proj.projectStarter.object.todo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TodoJobSearchReq {
    LocalDate deadlineStart;
    LocalDate deadlineEnd;
    JobStatus status;
    Integer jobCategoryId;
    String jobDescription;
    String jobName;

}
