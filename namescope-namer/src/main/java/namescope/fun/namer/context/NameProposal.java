package namescope.fun.namer.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameProposal {

    /**
     * 姓
     */
    private String lastName;

    /**
     * 名
     */
    private String firstName;

    /**
     * 起名原因
     */
    private String reason;

    /**
     * 原始返回值
     */
    private String rawResponse;

}
