package ac.proj.projectStarter;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueryDSLFactoryConfiguration {
    @Bean
    public JPAQueryFactory jpaQueryFactory(@Autowired EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
