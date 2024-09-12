package ac.proj.projectStarter.repo.todo;

import ac.proj.projectStarter.domain.QTodoJob;
import ac.proj.projectStarter.domain.QTodoJobCategory;
import ac.proj.projectStarter.domain.TodoJob;
import ac.proj.projectStarter.object.todo.TodoJobDTO;
import ac.proj.projectStarter.object.todo.TodoJobSearchReq;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class TodoJobRepositoryImpl implements TodoJobRepositoryCustom {
    // Main Reference : http://querydsl.com/static/querydsl/latest/reference/html/
    /*
    Custom Method generation :
    (1) INTERFACE TodoJobRepository EXTENDS JPARepository/xxxExecutor/TodoJobRepositoryCustom
    (2) CLASS TodoJobRepositoryImpl IMPLEMENTS TodoJobRepositoryCustom
    (3) Service Layer @Autowire TodoJobRepository
    Spring boot will auto-search for TodoJobRepositoryImpl -> for custom method implementation
       */
    @Autowired
    @Lazy
    TodoJobRepository todoRepository;

    //@Autowired
    @PersistenceContext
    EntityManager em;

    @Autowired
    private JPAQueryFactory queryFactory;

    // Most simple example for QueryDSL
    public List<TodoJob> searchTodoJobQueryDSL1() {
        QTodoJob todoJob = QTodoJob.todoJob;
        List<TodoJob> lst = queryFactory.selectFrom(todoJob)
                .where(todoJob.status.eq(1)).fetch();
        return lst;
    }

    // DEMO : QueryDSL
    // (1) Nested Predicate
    // (2) INNER JOIN with t_todo_job_category
    // (3) Date Comparison

    public List<TodoJob> searchTodoJobQueryDSL2() {
        QTodoJob todoJob = QTodoJob.todoJob;
        QTodoJobCategory todoJobCategory = QTodoJobCategory.todoJobCategory;
        /*
        select * from t_todo_job j
inner join t_todo_job_category jc on jc.cat_id = j.cat_id
WHERE jc.name = 'Personal'
AND ( j.job_details like '%Invoice%' )
AND ( j.deadline >= "YYYY-MM-DD" AND j.deadline <= "YYYY-MM-DD" )
.....
         */

        List<TodoJob> lst = queryFactory.selectFrom(todoJob)
                .innerJoin(todoJob.jobCategory, todoJobCategory)
                .where(todoJobCategory.categoryName.eq("Personal")
                        .and(todoJob.details.like("%Invoice%").or(todoJob.details.like("%None%")))
                        .and(todoJob.status.in(Arrays.asList(1,2,3)) )
                        .and(todoJob.deadline.between
                                (LocalDate.of(2020,1,1),LocalDate.of(2026,1,1) ))
                        .and(todoJob.deadline.after(LocalDate.of(2001,1,1)))
                ).fetch();
        // select(todoJob.details) return List<String>
        // select(todoJob.details,todoJob.importance) return List<Tuple>
        return lst;
    }
    public List<TodoJob> searchTodoJobQueryDSL3() {
        return null;
    }
    // Demo : QueryDSL
    // ExpressionUtils
    // DTO Projection
    public List<TodoJobDTO> searchTodoJobQueryDSL4() {
        QTodoJob todoJob = QTodoJob.todoJob;
        QTodoJobCategory todoJobCategory = QTodoJobCategory.todoJobCategory;

        Integer page = 1;
        Integer size = 1;
        TodoJobSearchReq searchReq = new TodoJobSearchReq();
        searchReq.setJobDetails("BookStore");
        searchReq.setJobCatNames(Arrays.asList("Personal", "Work" , "Dummy"));
        searchReq.setDeadlineEnd(LocalDate.of(2030,1,1));


        // Pattern I :
        /*
        Predicate p = todoJob.jobId.isNotNull(); // initialize something true
        if (!StringUtils.isBlank(searchReq.getJobDetails())) {
            p = ExpressionUtils.and(p,todoJob.details.like("%"+searchReq.getJobDetails()+"%"));
        }
        if (!CollectionUtils.isNotEmpty(searchReq.getJobCatNames())) {
            p = ExpressionUtils.and(p,todoJobCategory.categoryName.in(searchReq.getJobCatNames()));
        }
         */
        // Pattern II :
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add( todoJob.jobId.isNotNull());
        if (!StringUtils.isBlank(searchReq.getJobDetails())) {
            predicates.add(todoJob.details.like("%"+searchReq.getJobDetails()+"%"));
        }
        if (!CollectionUtils.isNotEmpty(searchReq.getJobCatNames())) {
            predicates.add(todoJobCategory.categoryName.in(searchReq.getJobCatNames()));
        }
        if  (searchReq.getDeadlineStart()!=null&&searchReq.getDeadlineEnd()!=null) {
            predicates.add(Expressions.asDate(todoJob.deadline)
                    .between(searchReq.getDeadlineStart(),searchReq.getDeadlineEnd()));
        } else if( (searchReq.getDeadlineStart()!=null)) {
            predicates.add(todoJob.deadline.after(searchReq.getDeadlineStart())
                    .or(todoJob.deadline.eq(searchReq.getDeadlineStart())));
        } else if ( (searchReq.getDeadlineEnd()!=null)) {
            predicates.add(todoJob.deadline.before(searchReq.getDeadlineEnd())
                    .or(todoJob.deadline.eq(searchReq.getDeadlineEnd())));
        }
        //List<TodoJob> lst = queryFactory.selectFrom(todoJob).where(ExpressionUtils.allOf(predicates)).fetch();
        /*
         String jobSummary;
    LocalDate deadLineDate;
    Integer status;
    String categoryName;
         */

        List<TodoJobDTO> lst = queryFactory.select(Projections.constructor(TodoJobDTO.class,
                        todoJob.details,todoJob.deadline,todoJob.status,todoJobCategory.categoryName))
                .from(todoJob)
                .innerJoin(todoJob.jobCategory, todoJobCategory)
                .where(ExpressionUtils.allOf(predicates))
                .orderBy(todoJob.jobId.desc(),todoJobCategory.categoryName.asc())
                .offset(0)
                .limit(10)
                .fetch();
        return lst;

    }
    // Demo : QueryDSL
    // (1) DTO Projection
    // (2) Paging and sorting
    // https://dzone.com/articles/jpa-querydsl-projections
    // https://juejin.cn/post/6844903935828836360
    //
    /*
    public List<TodoJob> searchTodoJobQueryDSL4() {
        TodoJobSearchReq searchReqObj = new TodoJobSearchReq();

        QTodoJob todoJob = QTodoJob.todoJob;
        QTodoJobCategory todoJobCategory = QTodoJobCategory.todoJobCategory;

        JPAQuery<TodoJob> query = new JPAQuery<>(em);
       // query.from(todoJob).
        List<TodoJobDTO> lst = queryFactory.select(Projection.bean(TodoJobDTo.class))
                .innerJoin(todoJob.jobCategory, todoJobCategory)
                .where(todoJobCategory.categoryName.eq("Personal")
                        .and(todoJob.details.like("%Invoice%").or(todoJob.details.like("%None%")))
                        .and(Expressions.asDate(todoJob.deadline)
                                .between(LocalDate.of(2020,1,1),LocalDate.of(2026,1,1) ))
                        .and(Expressions.asDate(todoJob.deadline).gt(LocalDate.of(2001,1,1)))
                ).fetch();
        return null;
    }

     */







    /*
    Useful QueryDSL Learning Materials :
    https://juejin.cn/post/6844903920956014606
    https://juejin.cn/post/6844903935828836360

     */
}
