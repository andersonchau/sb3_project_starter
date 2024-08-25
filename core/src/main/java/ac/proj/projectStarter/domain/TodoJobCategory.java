package ac.proj.projectStarter.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/*
CREATE TABLE t_todo_job_category (
cat_id BIGINT NOT NULL AUTO_INCREMENT,
parent_id BIGINT NULL,
name VARCHAR(200) DEFAULT NULL,
CONSTRAINT fk_todo_job_category_parent FOREIGN KEY (parent_id) REFERENCES t_todo_job_category(cat_id),
primary key(cat_id)
);
 */
@Entity
@Table(name = "t_todo_job_category")
public class TodoJobCategory {
// Useful reference : https://stackoverflow.com/questions/30334044/hibernate-same-table-parent-child-relation

    @Transient
    Long parentId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long catId;

    String name;
    // @ManyToOne -> (1) define Cardinality (2) Declare that I am at the many (child) side, optional = true -> nullable
    // @JoinColumn -> Tell Hibernate to gen SQL : to use my record's parent_id field to relate to parent table's PK field
    // for fetching
    @ManyToOne(fetch = FetchType.LAZY, optional=true)
    @JoinColumn(name="parent_id")
    private TodoJobCategory parent;


    // @OneToMany -> (1) define Cardinality (2) Declare that I am at the one (parent) side
    // mappedBy -> tell Hibernate to use remote_table.parent_id to join this table's PK for SQL
    @OneToMany(mappedBy="parent_id", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval=true)
    private Set<TodoJobCategory> children = new HashSet<TodoJobCategory>();

}
