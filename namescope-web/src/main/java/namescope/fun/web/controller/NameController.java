package namescope.fun.web.controller;

import jakarta.annotation.Resource;
import namescope.fun.namer.context.NameContext;
import namescope.fun.namer.context.NameProposal;
import namescope.fun.namer.infra.NamerDispatcher;
import namescope.fun.web.request.NameRequest;
import namescope.fun.web.response.NameResponse;
import namescope.fun.web.service.ParamService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/namescope")
public class NameController {

    @Resource
    private ParamService paramService;

    @Resource
    private NamerDispatcher namerDispatcher;

    @PostMapping("/propose")
    public NameResponse<NameProposal> propose(@RequestBody NameRequest nameRequest) {

        NameContext nameContext = paramService.init(nameRequest);

        namerDispatcher.process(nameContext);

        return NameResponse.success(nameContext.getNameProposal());
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

}
