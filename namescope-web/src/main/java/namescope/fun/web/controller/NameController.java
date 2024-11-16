package namescope.fun.web.controller;

import namescope.fun.web.request.NameRequest;
import namescope.fun.web.response.NameResponse;
import namescope.fun.web.vo.NameProposalVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/namescope")
public class NameController {

    @PostMapping("/propose")
    public NameResponse<NameProposalVO> propose(@RequestBody NameRequest nameRequest) {

        return NameResponse.success();
    }

}
