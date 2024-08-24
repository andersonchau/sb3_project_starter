package ac.proj.projectStarter;


import ac.proj.projectStarter.service.inf.test.TestingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Slf4j
@SpringBootTest
public class ProjectTests {
    @Autowired
    TestingService ts;
    // for mvn test to run :
    // (1) this class must be in /src/java/test/
    // (2) class name : xxxTests

    // mvn test to run all tests
    // To run all test : mvn test
    // All test of a class
    // mvn test -Dsurefire.failIfNoSpecifiedTests=false -Dtest=ProjectTests
    // Single test
    // mvn test -Dsurefire.failIfNoSpecifiedTests=false -Dtest=ProjectTests#methodName
    // To run test of particular class in a module :
    // mvn test -Dsurefire.failIfNoSpecifiedTests=false -Dtest=ProjectTests -pl webapp -am
    // To run test a method of particular class in a module  :
    // mvn test -Dsurefire.failIfNoSpecifiedTests=false -Dtest=ProjectTests#demoTesting2 -pl webapp -am
    // after running test,
    @Test
    void demoTesting1() {
        log.info("running demoTesting1");
        //System.out.println("Running demoTesting1 ###########################################################");
        // assertEquals("s", "x"); // trigger failed case
    }
    @Test
    void demoTesting2() {
        log.info("running demoTesting2");
        assertEquals("s", ts.simpleTestResult());
    }

    
}
