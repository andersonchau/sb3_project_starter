# Notes 
1. Starter project template (multi MVN modules) using Spring Boot 3 and Java 17
2. Some common features Implemented

# Building and Running Information
1. maven 3.9 , RedHat OpenJDK 17 
2. Building : mvn -Dmaven.test.skip=true clean package ; docker-compose up ;
3. 

## Features Implemented 
1. Using jrxml to generate PDF report using Jasper Report Library(ReportUtils.java)
2. Load file content from war/jar code classpath (ProjectFileUtils.java)
3. @SpringBootTest usage (ProjectTests.java, ProjectMvcTests.java)
4. Add logback configuration support (resource/logback-spring.xml)


## Features To be Implemented
1. AOP ( CSV to object , Validator ) 
2. Caching    
3. Quartz Scheduler + Spring Batch
4. GraphQL
5. JPA QueryDSL  
5. RabbitMQ read / write 
6. Multipart file upload 
7. Excel Generation 
8. Microservices 
9. MongoDB Access.
10. Solr
11. Apache POI
12. Crystal Report 
13. iText
14. OAuth2 Login with Google. 
15. Spring Security Login with JWT Token 
16. Thyemeleaf