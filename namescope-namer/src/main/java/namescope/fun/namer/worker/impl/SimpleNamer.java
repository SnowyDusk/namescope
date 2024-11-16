package namescope.fun.namer.worker.impl;


import namescope.fun.namer.context.NameContext;
import namescope.fun.namer.enums.NamerEnum;
import namescope.fun.namer.infra.Namer;
import namescope.fun.namer.worker.AbstractNamer;
import org.springframework.stereotype.Service;

@Namer(NamerEnum.DEFAULT)
@Service
public class SimpleNamer extends AbstractNamer {
    @Override
    protected void process(NameContext nameContext) {

    }
}
