package ac.proj.obj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum AppCode {
    APP_CODE_OK("OK"),
    APP_CODE_BUSINESS_OBJ_NOT_FOUND("Business Object Not Found");


    private String msg;


}
