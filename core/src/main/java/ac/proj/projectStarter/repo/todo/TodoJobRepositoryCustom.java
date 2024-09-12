package ac.proj.projectStarter.repo.todo;

import ac.proj.projectStarter.domain.TodoJob;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TodoJobRepositoryCustom {



    List<TodoJob> searchTodoJobQueryDSL1();
    List<TodoJob> searchTodoJobQueryDSL2();
}
