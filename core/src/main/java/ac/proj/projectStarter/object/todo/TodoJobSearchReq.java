package ac.proj.projectStarter.object.todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoJobSearchReq {
    LocalDate deadlineStart;
    LocalDate deadlineEnd;
    Integer status;
    List<String> jobCatNames;
    String jobSummary;
    String jobDetails;
    // Assume Paging and sorting information here.
}
