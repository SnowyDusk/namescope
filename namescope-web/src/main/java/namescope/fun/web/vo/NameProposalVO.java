package namescope.fun.web.vo;

import lombok.Data;

@Data
public class NameProposalVO {

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

}
