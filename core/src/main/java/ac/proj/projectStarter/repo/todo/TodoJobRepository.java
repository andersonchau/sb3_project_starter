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
    // Demo :  nativeSQL + Param + LIKE usage with Param
    // Note : if not using tjd.* but * => duplicate field of the join columns (cat_id)
    // Note : According to Hibernate log : This query will also induce 
    @Query(
        value = "SELECT tdj.* " +
                " FROM t_todo_job tdj " +
                " INNER JOIN t_todo_job_category tdjc ON tdjc.cat_id = tdj.cat_id" +
                " WHERE tdjc.name LIKE  CONCAT ( '%', :catName , '%' ) ",
        nativeQuery = true)
    public List<TodoJob> searchJobListByCategoryNameWithProjectionNativeSQL(@Param("catName") String categoryName);


    @Query(
            value = "SELECT j FROM TodoJob j JOIN TodoJobCategory c WHERE c.categoryName = :catName " )
    public List<TodoJob> getJobListByCategoryNameIgnoreCaseJPQL(@Param("catName") String inputCatName );

    @Query(
            value = "SELECT j FROM TodoJob j JOIN TodoJobCategory c WHERE c.categoryName LIKE CONCAT ('%', :catName , '%')" )
    public List<TodoJob> searchJobListByCategoryNameIgnoreCaseJPQL(@Param("catName") String inputCatName );


}