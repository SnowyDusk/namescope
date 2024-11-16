package namescope.fun.namer.worker;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import namescope.fun.namer.claim.NameClaim;
import namescope.fun.namer.context.NameContext;
import namescope.fun.namer.context.NameParam;
import namescope.fun.namer.infra.Nameable;

@Slf4j
abstract public class AbstractNamer implements Nameable {
    @Override
    public boolean access(NameContext nameContext) {
        return true;
    }

    @Override
    public void name(NameContext nameContext) {
        buildUserPrompt(nameContext);
        process(nameContext);
    }

    abstract protected void process(NameContext nameContext);

    public void buildUserPrompt(NameContext nameContext) {
        NameClaim nameClaim = nameContext.getNameClaim();
        NameParam nameParam = nameContext.getNameParam();
        if (StrUtil.isNotBlank(nameParam.getClaim())) {
            nameClaim.setUserPromptTitle(nameParam.getClaim());
        } else {
            nameClaim.setUserPromptTitle("我没有特殊要求，请你自由发挥，取一个你觉得最美丽的名字吧。");
        }
    }
}
