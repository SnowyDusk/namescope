package namescope.fun.namer.context;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import namescope.fun.namer.claim.NameClaim;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameContext {

    private NameParam nameParam;

    private NameClaim nameClaim;

}
