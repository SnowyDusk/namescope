package namescope.fun.web.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultEnum {

    SUCCESS("A-0000", "成功"),

    UNKNOWN_ERROR("A-9999", "未知错误");

    private final String code;

    private final String desc;

}
