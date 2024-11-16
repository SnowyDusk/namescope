package namescope.fun.namer.infra;

import namescope.fun.namer.enums.NamerEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Namer {
    NamerEnum value();
}
