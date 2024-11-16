package namescope.fun.namer.worker.impl;


import jakarta.annotation.Resource;
import namescope.fun.engine.bo.NameBO;
import namescope.fun.engine.chat.VolcanoModel;
import namescope.fun.namer.claim.NameClaim;
import namescope.fun.namer.context.NameContext;
import namescope.fun.namer.context.NameProposal;
import namescope.fun.namer.enums.NamerEnum;
import namescope.fun.namer.infra.Namer;
import namescope.fun.namer.worker.AbstractNamer;
import org.springframework.stereotype.Service;

@Namer(NamerEnum.DEFAULT)
@Service
public class SimpleNamer extends AbstractNamer {

    @Resource
    private VolcanoModel volcanoModel;

    @Override
    protected void process(NameContext nameContext) {
        NameBO nameBO = buildNameBo(nameContext.getNameClaim());
        volcanoModel.name(nameBO);
        setResponse(nameContext, nameBO);
    }

    private NameBO buildNameBo(NameClaim nameClaim) {
        NameBO nameBO = new NameBO();
        nameBO.setSystemPrompt(nameClaim.getSystemPrompt());
        nameBO.setUserPrompt(nameClaim.getUserPrompt());
        return nameBO;
    }

    private void setResponse(NameContext context, NameBO nameBO) {
        NameProposal nameProposal = new NameProposal();
        nameProposal.setRawResponse(nameBO.getAssistantResponse());
        context.setNameProposal(nameProposal);
    }
}
