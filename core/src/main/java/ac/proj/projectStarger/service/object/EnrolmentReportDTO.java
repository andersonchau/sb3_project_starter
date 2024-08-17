package ac.proj.projectStarger.service.object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
