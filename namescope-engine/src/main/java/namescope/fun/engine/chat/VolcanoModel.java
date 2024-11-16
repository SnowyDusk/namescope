package namescope.fun.engine.chat;

import com.volcengine.ark.runtime.model.completion.chat.*;
import com.volcengine.ark.runtime.service.ArkService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import namescope.fun.engine.bo.NameBO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class VolcanoModel {

    @Resource
    private ArkService arkService;

    @Value("${volcano.endpoint}")
    private String endpoint;

    public void name(NameBO nameBO) {
        try {
            final List<ChatMessage> messages = Arrays.asList(
                    ChatMessage.builder().role(ChatMessageRole.SYSTEM).content(nameBO.getSystemPrompt()).build(),
                    ChatMessage.builder().role(ChatMessageRole.USER).content(nameBO.getUserPrompt()).build()
            );

            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    .model(endpoint)
                    .messages(messages)
                    .build();

            ChatCompletionResult completion = arkService.createChatCompletion(request);
            String content = completion.getChoices().getFirst().getMessage().getContent().toString();
            nameBO.setAssistantResponse(content);
        } catch (Exception e) {
            log.error("[volcano model] calling AI failed \n", e);
        }
    }

}
