package ac.proj.projectStarter.rest.test;

import ac.proj.projectStarter.domain.TodoJobCategory;
import ac.proj.projectStarter.object.test.MVCTestRequestDTO;
import ac.proj.projectStarter.object.test.MVCTestRespDTO;
import ac.proj.projectStarter.repo.todo.TodoJobCategoryRepository;
import ac.proj.projectStarter.service.inf.test.TestingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
public class TestingResultController {

    @Autowired
    TestingService ts;

    @Autowired
    TodoJobCategoryRepository todoJobCatRepo;

    @GetMapping("/testing1")
    public String get() {
        ts.testRetrieveFile();
        return "OK";
    }

    @GetMapping("/testing2")
    public String get2() {
        ts.testGeneratePDFReport();
        return "OK";
    }

    @GetMapping("/testing3")
    public String get3() {
        // (1)
        List<TodoJobCategory> lst = todoJobCatRepo.findAll();
        return "OK";
    }

    @PostMapping("/testIncrement")
    public MVCTestRespDTO get3(@RequestBody MVCTestRequestDTO request) {
        //System.out.println(request);
        return ts.mvcTestIncrement(request);
    }
}
