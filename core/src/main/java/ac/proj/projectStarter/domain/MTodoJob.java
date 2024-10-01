package ac.proj.projectStarter.domain;


import java.util.Date;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;


@Data
@ToString
@Document("todo_job")
public class MTodoJob {
    // MongoDB Entity
    @Id
    private String id;
    String category;
    String summary;
    Date deadline;
    Integer status;
    Integer importance;

}
