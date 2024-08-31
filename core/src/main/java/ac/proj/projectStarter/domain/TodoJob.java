package ac.proj.projectStarter.domain;

import ac.proj.projectStarter.object.todo.Importance;
import ac.proj.projectStarter.object.todo.JobStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

/*
CREATE TABLE t_todo_job (
	job_id BIGINT NOT NULL AUTO_INCREMENT ,
	cat_id BIGINT NOT NULL,
	job_desc VARCHAR(100) NOT NULL,
	job_details VARCHAR(1000) NOT NULL,
	deadline DATE NULL,
	status INT NOT NULL,
    importance INT NOT NULL DEFAULT 0,
	primary_key(job_id),
	CONSTRAINT fk_job_cat FOREIGN KEY (cat_id) REFERENCES t_todo_job_category(cat_id),
);
 */
@Entity
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
