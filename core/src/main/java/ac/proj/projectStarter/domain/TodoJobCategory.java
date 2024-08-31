package ac.proj.projectStarter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "t_todo_job_category")
@Data
public class TodoJobCategory {
// Useful reference : https://stackoverflow.com/questions/30334044/hibernate-same-table-parent-child-relation

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long catId;

    @Column(name = "name")
    String categoryName;
    // @ManyToOne -> (1) define Cardinality (2) Declare that I am at the many (child) side, optional = true -> nullable
    // @JoinColumn -> Tell Hibernate to gen SQL : to use my record's parent_id field to relate to parent table's PK field
    // for fetching
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent")
    private TodoJobCategory parent;



}
