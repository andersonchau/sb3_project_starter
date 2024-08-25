package ac.proj.projectStarter.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "t_todo_job_category")
public class TodoJobCategory {

    /*
    CREATE TABLE t_todo_job_category (
  cat_id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(200) DEFAULT NULL,
  primary key(cat_id)
);
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long catId;
    String name;


}
