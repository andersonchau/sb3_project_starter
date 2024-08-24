package ac.proj.obj;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppResponseEntity<T> {
    private String appCode;
    private String codeMsg;
    private T businessObj;
}
