package com.jenjenuwu.gptminecraft.openAI;

import com.jenjenuwu.gptminecraft.config.ModConfig;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import me.shedaniel.autoconfig.AutoConfig;

import java.util.List;

public class OpenAiModel {
    private static ModConfig config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

    private static List<ChatMessage> getMessages(String Prompt, String SystemPrompt) {
        return List.of(new ChatMessage("user", Prompt), new ChatMessage("system", SystemPrompt));
    }

    private static ChatCompletionRequest createChatCompletionRequestBuilder(List<ChatMessage> Prompt) {
        return ChatCompletionRequest.builder()
                .model("gpt-4")
                .messages(Prompt)
                .build();
    }


    private static OpenAiService createOpenAiService() {
        return new OpenAiService(config.getApiKey());
    }

    public static String getResponse(String userPrompt, String systemPrompt) {
        try {
            return createOpenAiService().createChatCompletion(createChatCompletionRequestBuilder(getMessages(userPrompt, systemPrompt))).getChoices().get(0).getMessage().getContent().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
