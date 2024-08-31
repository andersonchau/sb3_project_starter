package ac.proj.projectStarter.repo.todo;

import ac.proj.projectStarter.domain.TodoJobCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface TodoJobCategoryRepository extends
        JpaRepository<TodoJobCategory,Long>,
        JpaSpecificationExecutor<TodoJobCategory>,
        QuerydslPredicateExecutor<TodoJobCategory>,
        TodoJobCategoryRepositoryCustom
{
//https://docs.spring.io/spring-data/jpa/reference/repositories/custom-implementations.html
//https://medium.com/@bubu.tripathy/best-practices-creating-repository-interfaces-with-jpa-d904bee64397

}
