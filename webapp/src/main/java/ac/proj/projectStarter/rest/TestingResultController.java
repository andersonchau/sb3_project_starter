package ac.proj.projectStarter.rest;

import ac.proj.projectStarger.service.object.MVCTestRequestDTO;
import ac.proj.projectStarger.service.object.MVCTestRespDTO;
import ac.proj.projectStarter.service.inf.TestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestingResultController {

    @Autowired
    TestingService ts;

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

    @PostMapping("/testIncrement")
    public MVCTestRespDTO get3(@RequestBody MVCTestRequestDTO request) {
        System.out.println(request);
        return ts.mvcTestIncrement(request);

    }
}
