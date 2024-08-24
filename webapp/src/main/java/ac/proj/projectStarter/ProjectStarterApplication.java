package ac.proj.projectStarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages= {
//		"ac.proj",
//		"ac.proj.projectStarter.service.inf"})
//@ComponentScan({"com.my.package.first","com.my.package.second"})
@SpringBootApplication
//@ComponentScan(basePackageClasses = {TestingService.class})
@ComponentScan({"ac.proj.projectStarter.service.inf","ac.proj.projectStarter.rest","ac.proj.projectStarter"})
public class ProjectStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectStarterApplication.class, args);
	}

}
