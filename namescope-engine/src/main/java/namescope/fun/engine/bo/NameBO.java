package namescope.fun.engine.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameBO {

    private String systemPrompt;

    private String userPrompt;

    private String assistantResponse;

}
