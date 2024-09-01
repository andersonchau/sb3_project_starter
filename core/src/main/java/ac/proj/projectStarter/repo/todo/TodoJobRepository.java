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

    // Demo : JPQL (with LIKE and WHERE IN)
    // Note : Need to define @ManyToOne to make it work
    // ANSON
    @Query( value = "SELECT j FROM TodoJob j JOIN j.jobCategory WHERE j.details LIKE CONCAT('%',:jobDesc ,'%') AND j.jobCategory.categoryName IN :catNames" )
    public List<TodoJob> searchJobListByCategoryNameJPQL(@Param("catNames") List<String> categoryNames,
                                                                @Param("jobDesc") String jobDescription );
    /*

     */
    // Demo : JPQL with DTO projection
    // see more : https://stackoverflow.com/questions/73960617/how-to-pass-a-select-statement-as-an-argument-to-a-constructor-expression-in-jpq for some output field variation
    // seems there is problem here : https://discourse.hibernate.org/t/possible-regresssion-in-semanticquerybuilder-6-4-4-6-5-2/9612
    @Query( value = "SELECT new ac.proj.projectStarter.object.todo.TodoJobDTO(j.description,j.deadline,j.getStatusIntValue() ,j.jobCategory.categoryName) " +
            " FROM TodoJob j JOIN j.jobCategory " +
            "WHERE j.details LIKE CONCAT('%',:jobDesc ,'%') AND j.jobCategory.categoryName IN :catNames" )
    public List<TodoJobDTO> searchJobListByCategoryNameIgnoreCaseJPQL(@Param("catName") String inputCatName );



}