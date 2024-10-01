package ac.proj.projectStarter.repo.todo;


import ac.proj.projectStarter.domain.MTodoJob;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

// TODO :
// Paging

public interface MongoTodoJobRepository extends MongoRepository<MTodoJob, String> {


    List<MTodoJob> findByCategory(String catName);

    @Query("{category:'?0',importance:'?1',status:1}")
    List<MTodoJob> findByCategoryWithLowImportanceJob(String catName, Integer importance);


}