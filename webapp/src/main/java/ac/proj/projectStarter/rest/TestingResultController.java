package ac.proj.projectStarter.rest;

import ac.proj.projectStarter.service.inf.TestingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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


}
