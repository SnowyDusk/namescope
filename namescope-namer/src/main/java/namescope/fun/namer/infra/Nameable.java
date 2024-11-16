package namescope.fun.namer.infra;


import namescope.fun.namer.context.NameContext;

public interface Nameable {

    boolean access(NameContext nameContext);

    void name(NameContext nameContext);

}
