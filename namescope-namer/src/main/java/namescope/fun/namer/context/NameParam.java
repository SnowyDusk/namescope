package namescope.fun.namer.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameParam {
    /**
     * 姓
     */
    private String lastName;

    /**
     * 工作流
     */
    private Integer namerId;

    /**
     * 要求
     */
    private String claim;

}
