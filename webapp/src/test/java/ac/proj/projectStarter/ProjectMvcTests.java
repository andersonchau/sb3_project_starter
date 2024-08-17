package ac.proj.projectStarter;


import ac.proj.projectStarger.service.object.MVCTestRequestDTO;
import ac.proj.projectStarter.rest.TestingResultController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// In MVC test mode, SB won't load full test
@WebMvcTest(TestingResultController.class)
public class ProjectMvcTests {

    @Autowired
    private MockMvc mockMvc;

    //@Autowired
    //private ObjectMapper objectMapper;
// mvn test -Dsurefire.failIfNoSpecifiedTests=false -Dtest=ProjectMvcTests#testAPI -pl webapp -am

    @Test
    public void testAPI() throws Exception {
        System.out.println("testAPI called");
        // return string without request body -- HTTP POST
        //mockMvc.perform(post("/testIncrement"))
        //        .andDo(print())
        //        .andExpect(status().isOk())
        //        .andExpect(content().string("OK"));
        mockMvc.perform(MockMvcRequestBuilders.post("/testIncrement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content((new ObjectMapper()).writeValueAsString(new MVCTestRequestDTO(1)))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.outputValue").value(2));

    }
}
