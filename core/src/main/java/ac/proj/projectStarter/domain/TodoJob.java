package ac.proj.projectStarter.domain;

import ac.proj.projectStarter.object.todo.Importance;
import ac.proj.projectStarter.object.todo.JobStatus;
import ac.proj.projectStarter.object.todo.TodoJobDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity


/*
String jobSummary;
LocalDate deadLineDate;
Integer status;
String categoryName;


*/
@NamedNativeQuery(
        name = "searchByJobCatAndDetailsNamedQuery",
        query =
                "SELECT tdj.job_summary AS jobSummary, " +
                        " tdj.deadline AS deadLineDate, " +
                        " tdj.status As status," +
                        " tdjc.name as categoryName " +
                        " FROM t_todo_job tdj " +
                        " INNER JOIN t_todo_job_category tdjc ON tdjc.cat_id = tdj.cat_id" +
                        " WHERE tdj.job_details LIKE  CONCAT ( '%', :jobDesc , '%' ) " +
                        " AND tdjc.name IN (:catNames)",
        resultSetMapping = "job_to_dto_map"
)
@SqlResultSetMapping(
        name = "job_to_dto_map",
        classes = @ConstructorResult(
                targetClass = TodoJobDTO.class,
                columns = {
                        @ColumnResult(name = "jobSummary", type = String.class),
                        @ColumnResult(name = "deadLineDate", type = LocalDate.class),
                        @ColumnResult(name = "status", type = Integer.class),
                        @ColumnResult(name = "categoryName", type = String.class),
                }
        )
)
@Table(name = "t_todo_job")
@Data
@ToString
public class TodoJob {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long jobId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cat_id")
    TodoJobCategory jobCategory;

    @Column(name="job_desc")
    String description;

    @Column(name="job_details")
    String details;

    @Column(name="deadline")
    LocalDate deadline;

    // Translated to 1,2,3.... , EnumType.STRING to String
    @Enumerated(EnumType.ORDINAL)
    JobStatus status;

    @Enumerated(EnumType.ORDINAL)
    Importance importance;


}
