package namescope.fun.namer.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NamerEnum {

    DEFAULT(0, "SimpleNamer", "默认工作流");

    private final Integer NamerId;

    private final String NamerType;

    private final String desc;
}
