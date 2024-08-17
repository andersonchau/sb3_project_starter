package ac.proj.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class ReportUtils {
    //https://blog.csdn.net/Mrqiang9001/article/details/113780831
    public static byte[] generatePdfReport(
            String jrxmlFileStr ,
            Map<String, Object> parameters,
            List<? extends Object> rows   ) {
        try {
            InputStream reportTemplate = new ByteArrayInputStream(jrxmlFileStr.getBytes(StandardCharsets.UTF_8));

            JasperReport jasperReport = JasperCompileManager.compileReport(reportTemplate);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rows);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            byte[] reportBytes = JasperExportManager.exportReportToPdf(jasperPrint);
            return reportBytes;
        } catch (Exception ex ) {
            ex.printStackTrace();
            return null;
        }



    }
}
