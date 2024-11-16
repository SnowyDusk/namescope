package namescope.fun.namer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NamerEnum {

    DEFAULT(0, "simpleNamer", "DefaultClaim", "默认工作流");

    private final Integer NamerId;

    private final String NamerType;

    private final String claimName;

    private final String desc;
}
