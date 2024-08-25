package ac.proj.projectStarter.service.inf.job;

import ac.proj.projectStarter.object.todo.JobStatus;
import ac.proj.projectStarter.object.todo.TodoJobDTO;
import ac.proj.projectStarter.object.todo.TodoJobSearchReq;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class TodoJobService {

    public List<TodoJobDTO> searchJob(TodoJobSearchReq searchReq) {
        var res = Arrays.asList(
                new TodoJobDTO("Job Description 1" , LocalDateTime.of(2019, 11, 30, 15, 16, 17), JobStatus.ACTIVE),
                new TodoJobDTO("Job Description 2" , LocalDateTime.of(2024, 11, 30, 15, 16, 17), JobStatus.ACTIVE));
        return res;
    }


 }
