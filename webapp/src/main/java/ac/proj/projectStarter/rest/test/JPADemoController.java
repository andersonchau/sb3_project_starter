package ac.proj.projectStarter.rest.test;

import ac.proj.projectStarter.domain.TodoJob;
import ac.proj.projectStarter.object.todo.TodoJobDTO;
import ac.proj.projectStarter.object.todo.TodoJobSearchReq;
import ac.proj.projectStarter.repo.todo.TodoJobCategoryRepository;
import ac.proj.projectStarter.repo.todo.TodoJobRepository;
import ac.proj.projectStarter.service.inf.test.TestingService;
import ac.proj.util.DebugUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class JPADemoController {
    @Autowired
    TestingService ts;

    @Autowired
    TodoJobCategoryRepository todoJobCatRepo;

    @Autowired
    TodoJobRepository todoJobRepo;

    @GetMapping("/jpa/testing1")
    public String get() {
        // 1. Some JPARepository functions

        List<TodoJob> jobList = todoJobRepo.searchJobListByCategoryNameNativeSQL(Arrays.asList("Work", "Personal"),"Customer");
        DebugUtils.printList(jobList, "searchJobListByCategoryNameNativeSQL");

        // 2. named query + Projection
        List<TodoJobDTO> jobDTOList = todoJobRepo.searchJobListByCategoryNameProjectionNativeSQL(Arrays.asList("Work", "Personal"),"Customer");
        DebugUtils.printList(jobDTOList, "searchJobListByCategoryNameProjectionNativeSQL");


        // 3. JPQL
        List<TodoJob> jobList2 = todoJobRepo.searchJobListByCategoryNameJPQL(Arrays.asList("Work", "Personal"),"Customer");
        DebugUtils.printList(jobList2, "searchJobListByCategoryNameJPQL");

        // 4. JPQL
        TodoJobSearchReq searchReq = new TodoJobSearchReq();
        searchReq.setJobCatNames(Arrays.asList(new String[] { "Work","Personal" }));
        //searchReq.setJobDetails("o");
        // starts from pageNumber starts from 0
        Pageable pagingInfo = PageRequest.of(1, 2,
                Sort.by("deadline").descending().and(Sort.by("status")));
        List<TodoJob> jobList3 = todoJobRepo.searchJobListByCategoryNamePagingJPQL(searchReq,pagingInfo);
        DebugUtils.printList(jobList3, "searchJobListByCategoryNamePagingJPQL");

        return "OK";
    }
}
