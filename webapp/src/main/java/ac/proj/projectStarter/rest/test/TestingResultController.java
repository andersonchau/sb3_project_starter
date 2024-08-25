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
        /* Result of this code :
        Hibernate: select tjc1_0.cat_id,tjc1_0.name,tjc1_0.parent_id from t_todo_job_category tjc1_0
        00:24:23.200 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - =====================================================
        00:24:23.200 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - Job Category Item : Work
        Hibernate: select c1_0.parent_id,c1_0.cat_id,c1_0.name from t_todo_job_category c1_0 where c1_0.parent_id=?
        Hibernate: select c1_0.parent_id,c1_0.cat_id,c1_0.name from t_todo_job_category c1_0 where c1_0.parent_id=?
        Hibernate: select c1_0.parent_id,c1_0.cat_id,c1_0.name from t_todo_job_category c1_0 where c1_0.parent_id=?
        00:24:23.213 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - =====================================================
        00:24:23.213 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - Job Category Item : Personal
        Hibernate: select c1_0.parent_id,c1_0.cat_id,c1_0.name from t_todo_job_category c1_0 where c1_0.parent_id=?
        Hibernate: select c1_0.parent_id,c1_0.cat_id,c1_0.name from t_todo_job_category c1_0 where c1_0.parent_id=?
        Hibernate: select c1_0.parent_id,c1_0.cat_id,c1_0.name from t_todo_job_category c1_0 where c1_0.parent_id=?
        Hibernate: select c1_0.parent_id,c1_0.cat_id,c1_0.name from t_todo_job_category c1_0 where c1_0.parent_id=?
        00:24:23.223 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - =====================================================
        00:24:23.224 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - Job Category Item : Project One
        00:24:23.225 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - has Parent: Work
        00:24:23.225 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - =====================================================
        00:24:23.226 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - Job Category Item : Health
        00:24:23.226 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - has Parent: Personal
        00:24:23.226 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - =====================================================
        00:24:23.227 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - Job Category Item : Finance
        00:24:23.227 [http-nio-9000-exec-1] INFO  a.p.p.r.test.TestingResultController - has Parent: Personal
        */

        List<TodoJobCategory> lst = todoJobCatRepo.findAll();
        for(TodoJobCategory tc : lst ) {
            log.info("=====================================================");
            log.info("Job Category Item : " + tc.getName());
            Set<TodoJobCategory> children = tc.getChildren();
         //   if ( !CollectionUtils.isEmpty(children) ) { // why call N time for each
           //     for ( TodoJobCategory tcc : children ) {
             //       log.info("has children : " + tcc.getName() );
         //       }
            //}
            if ( tc.getParent() != null ) {
                log.info("has Parent: "+ tc.getParent().getName());
            }
        }
        return "OK";
    }

    @PostMapping("/testIncrement")
    public MVCTestRespDTO get3(@RequestBody MVCTestRequestDTO request) {
        System.out.println(request);
        return ts.mvcTestIncrement(request);

    }
}
