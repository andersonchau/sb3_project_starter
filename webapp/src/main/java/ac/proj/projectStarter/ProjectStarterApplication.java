package ac.proj.projectStarter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication(scanBasePackages= {
//		"ac.proj",
//		"ac.proj.projectStarter.service.inf"})
//@ComponentScan({"com.my.package.first","com.my.package.second"})
@SpringBootApplication
// find @Service/@Controller ...
@ComponentScan({"ac.proj.projectStarter.service.inf","ac.proj.projectStarter.rest","ac.proj.projectStarter"})
@EnableJpaAuditing
@EntityScan({ "ac.proj.projectStarter.domain"}) // find @Entity classes
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"ac.proj.projectStarter.repo.todo"}) // find @Repository
public class ProjectStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectStarterApplication.class, args);
	}

}
