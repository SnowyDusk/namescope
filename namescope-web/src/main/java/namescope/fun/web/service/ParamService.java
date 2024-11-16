package namescope.fun.web.service;

import jakarta.annotation.Resource;
import namescope.fun.namer.context.NameContext;
import namescope.fun.namer.context.NameParam;
import namescope.fun.web.request.NameRequest;
import namescope.fun.web.util.BeanConverter;
import org.springframework.stereotype.Service;

@Service
public class ParamService {

    @Resource
    private BeanConverter beanConverter;

    public NameContext init(NameRequest nameRequest) {
        NameContext nameContext = new NameContext();
        NameParam nameParam = beanConverter.convert(nameRequest);
        nameContext.setNameParam(nameParam);
        return nameContext;
    }

}
