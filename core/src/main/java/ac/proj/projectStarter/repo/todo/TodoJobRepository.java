package ac.proj.projectStarter.repo.todo;

import ac.proj.projectStarter.domain.TodoJob;
import ac.proj.projectStarter.domain.TodoJobCategory;
import ac.proj.projectStarter.object.todo.JobStatus;
import ac.proj.projectStarter.object.todo.TodoJobDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface TodoJobRepository extends
        JpaRepository<TodoJob,Long>,
        JpaSpecificationExecutor<TodoJob>,
        QuerydslPredicateExecutor<TodoJob>,
        TodoJobCategoryRepositoryCustom
{
//https://docs.spring.io/spring-data/jpa/reference/repositories/custom-implementations.html
//https://medium.com/@bubu.tripathy/best-practices-creating-repository-interfaces-with-jpa-d904bee64397

    // https://blog.csdn.net/isyoungboy/article/details/87878685
    // Demo :  nativeSQL + Param + LIKE usage with Param + with WHERE IN <List>
    // Note : if not using tjd.* but * => duplicate field of the join columns (cat_id)
    // Note : According to Hibernate log : This query will also induce N+1 problem
    @Query(
        value = "SELECT tdj.* " +
                " FROM t_todo_job tdj " +
                " INNER JOIN t_todo_job_category tdjc ON tdjc.cat_id = tdj.cat_id" +
                " WHERE tdj.job_details LIKE  CONCAT ( '%', :jobDesc , '%' ) " +
                " AND tdjc.name IN (:catNames)",
        nativeQuery = true)
    public List<TodoJob> searchJobListByCategoryNameNativeSQL(@Param("catNames") List<String> categoryNames,
                                                              @Param("jobDesc") String jobDescription );

    // Demo :  nativeSQL + named query + DTO projection
    // Note : this does not have N+1 problem because we are just accessing the DTO for category name field, which is not a managed @Entity object
    @Query( name = "searchByJobCatAndDetailsNamedQuery" , nativeQuery = true )
    public List<TodoJobDTO> searchJobListByCategoryNameProjectionNativeSQL(@Param("catNames") List<String> categoryNames,
                                                                        @Param("jobDesc") String jobDescription );

    

    @Query(
            value = "SELECT j FROM TodoJob j JOIN TodoJobCategory c WHERE c.categoryName = :catName " )
    public List<TodoJob> getJobListByCategoryNameIgnoreCaseJPQL(@Param("catName") String inputCatName );

    @Query(
            value = "SELECT j FROM TodoJob j JOIN TodoJobCategory c WHERE c.categoryName LIKE CONCAT ('%', :catName , '%')" )
    public List<TodoJob> searchJobListByCategoryNameIgnoreCaseJPQL(@Param("catName") String inputCatName );


}