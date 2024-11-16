package namescope.fun.namer.claim;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.lang.StringTemplate.STR;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NameClaim {

    @JsonProperty
    private String systemPromptTitle;

    @JsonProperty
    private List<String> systemPrompts;

    @JsonProperty
    private List<String> textBooks;

    @JsonProperty
    private String systemAnnex;

    @JsonProperty
    private String userPromptsTitle;

    @JsonProperty
    private List<String> userPrompts;

    public String getSystemPrompt() {
        StringBuilder sb = new StringBuilder();
        sb.append(systemPromptTitle);

        if (!CollectionUtils.isEmpty(systemPrompts)) {
            sb.append("\n\n请遵守以下规则: \n");
            for (int index = 0; index < systemPrompts.size(); index++) {
                String require = STR."# \{index}. \{systemPrompts.get(index)}\n";
                sb.append(require);
            }
        }

        if (!CollectionUtils.isEmpty(textBooks)) {
            sb.append("\n\n 你总结了许多取名技巧，掌握这些取名方法，你总是能为每个人想出好名字: \n");
            for (int index = 0; index < textBooks.size(); index++) {
                String textBook = STR."\{index}. \{textBooks.get(index)}\n";
                sb.append(textBook);
            }
            sb.append("这些只是一部分起名技巧，要想出一个令人满意的名字有时候不需要这些技巧，而是需要你发挥聪明才智！");
        }

        if (!StrUtil.isBlank(systemAnnex)) {
            sb.append("\n\n请参考以下资料:\n");
            sb.append(systemAnnex);
        }
        return sb.toString();
    }

    public String getUserPrompt() {
        StringBuilder sb = new StringBuilder();
        sb.append(userPromptsTitle);

        if (!CollectionUtils.isEmpty(userPrompts)) {
            sb.append("\n\n以下是具体说明:\n");
            for (int index = 0; index < userPrompts.size(); index++) {
                String require = STR."# \{index}. \{userPrompts.get(index)}\n";
                sb.append(require);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        File file = ResourceUtils.getFile("classpath:prompt/DefaultClaim.json");
        NameClaim nameClaim = mapper.readValue(file, NameClaim.class);
        System.out.println(nameClaim);
    }
}
