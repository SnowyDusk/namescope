package namescope.fun.namer.worker;

import lombok.extern.slf4j.Slf4j;
import namescope.fun.namer.context.NameContext;
import namescope.fun.namer.infra.Nameable;

@Slf4j
abstract public class AbstractNamer implements Nameable {
    @Override
    public boolean access(NameContext nameContext) {
        return true;
    }

    @Override
    public void name(NameContext nameContext) {
        process(nameContext);
    }

    abstract protected void process(NameContext nameContext);
}
