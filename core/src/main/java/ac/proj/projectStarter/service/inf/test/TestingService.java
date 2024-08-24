package ac.proj.projectStarter.service.inf.test;
import ac.proj.projectStarger.service.object.report.dto.EnrolmentReportDTO;
import ac.proj.projectStarger.service.object.test.MVCTestRequestDTO;
import ac.proj.projectStarger.service.object.test.MVCTestRespDTO;
import ac.proj.util.FileLocType;
import ac.proj.util.ProjectFileUtils;
import ac.proj.util.ReportUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class TestingService {
    // Demonstrate How to retrieve file in app/src/main/resources i.e. /WEB-INF/resources in jar or war
    public void testRetrieveFile() {
        try {
            System.out.println(ProjectFileUtils.getFileContentAsString(FileLocType.CLASS_PATH, "report/testFile.txt"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Demonstrate how generate
    public void testGeneratePDFReport() {
        try {
            var reportParams = new HashMap<String, Object>();
            reportParams.put("reportTitle", "Report Title");
            var rows = Arrays.asList(
                    new EnrolmentReportDTO(1, "R01212", "Chan Tai Man", new java.sql.Date(System.currentTimeMillis()), 1),
                    new EnrolmentReportDTO(2, "R01213", "Chan Man Wai", new java.sql.Date(System.currentTimeMillis()), 2),
                    new EnrolmentReportDTO(3, "R01214", "David Wong", new java.sql.Date(System.currentTimeMillis()), 1)
            );
            String jrxml = ProjectFileUtils.getFileContentAsString(FileLocType.CLASS_PATH, "report/testing.jrxml");
            var bytes = ReportUtils.generatePdfReport(jrxml, reportParams, rows);
            ProjectFileUtils.saveFile("c:/temp/a.pdf", bytes);
            // public static byte[] generatePdfReport(
            //         String jrxmlFileStr ,
            //         Map<String, Object> parameters,
            //         List<? extends Object> rows   )
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String simpleTestResult() {
        return "s";
    }


    public MVCTestRespDTO mvcTestIncrement(MVCTestRequestDTO req) {
        log.info("mvcTestIncrement called");
        MVCTestRespDTO resp = new MVCTestRespDTO();
        resp.setOutputValue(req.getInputValue()+1);
        resp.setResult("OK");
        // TODO : add repository data access
        return resp;
    }


}
