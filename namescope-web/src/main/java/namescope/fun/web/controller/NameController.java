package namescope.fun.web.controller;

import jakarta.annotation.Resource;
import namescope.fun.namer.context.NameContext;
import namescope.fun.namer.infra.NamerDispatcher;
import namescope.fun.web.request.NameRequest;
import namescope.fun.web.response.NameResponse;
import namescope.fun.web.service.ParamService;
import namescope.fun.web.vo.NameProposalVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/namescope")
public class NameController {

    @Resource
    private ParamService paramService;

    @Resource
    private NamerDispatcher namerDispatcher;

    @PostMapping("/propose")
    public NameResponse<NameProposalVO> propose(@RequestBody NameRequest nameRequest) {

        NameContext nameContext = paramService.init(nameRequest);

        namerDispatcher.process(nameContext);

        return NameResponse.success();
    }

}
