package ac.proj.projectStarter.repo.todo;

import ac.proj.projectStarter.domain.QTodoJob;
import ac.proj.projectStarter.domain.TodoJob;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoJobRepositoryImpl implements TodoJobRepositoryCustom {
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

    public List<TodoJob> searchTodoJobQueryDSL() {
        QTodoJob todoJob = QTodoJob.todoJob;
        List<TodoJob> lst = queryFactory.selectFrom(todoJob).where(todoJob.status.eq(1)).fetch();
        return lst;

    }



    /*
    Useful QueryDSL Learning Materials :
    https://juejin.cn/post/6844903920956014606
    https://juejin.cn/post/6844903935828836360

     */
}
