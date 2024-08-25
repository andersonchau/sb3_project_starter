package ac.proj.projectStarter.service.inf.job.impl;

import ac.proj.projectStarter.domain.TodoJobCategory;
import ac.proj.projectStarter.repo.todo.TodoJobCategoryRepository;
import ac.proj.projectStarter.service.inf.job.TodoJobCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoJobCategoryServiceImpl implements TodoJobCategoryService {
    @Autowired
    TodoJobCategoryRepository jobCategoryRepo;

    public void testAccessData() {
        List<TodoJobCategory> jobCatList =  jobCategoryRepo.findAll();
        log.info(jobCatList.toString());
    }


}
