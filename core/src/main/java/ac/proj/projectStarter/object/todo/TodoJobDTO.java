package ac.proj.projectStarter.object.todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoJobDTO {
    String description;
    LocalDateTime deadLine;
    JobStatus status;


}
