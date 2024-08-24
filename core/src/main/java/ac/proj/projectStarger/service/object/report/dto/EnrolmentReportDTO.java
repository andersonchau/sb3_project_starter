package ac.proj.projectStarger.service.object.report.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;
@Data
@AllArgsConstructor
public class EnrolmentReportDTO {
    protected Integer num;
    protected String appointRefNo;
    protected String applicantName;
    protected Date enrolDate;
    protected Integer numSeat;


}
