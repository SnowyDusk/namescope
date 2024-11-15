package namescope.fun.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameRequest {

    /**
     * 姓
     */
    private String lastName;

    /**
     * 工作流
     */
    private Integer worker;

    /**
     * 要求
     */
    private String claim;

}
