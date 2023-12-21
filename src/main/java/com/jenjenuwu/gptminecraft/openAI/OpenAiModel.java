package com.jenjenuwu.gptminecraft.openAI;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.List;

public class OpenAiModel {
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
        return new OpenAiService("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");
    }

    public static String getResponse(String userPrompt, String systemPrompt) {
        try {
            return createOpenAiService().createChatCompletion(createChatCompletionRequestBuilder(getMessages(userPrompt, systemPrompt))).getChoices().get(0).getMessage().getContent().trim();
        } catch (Exception e) {
            // Handle exceptions appropriately in your application
            e.printStackTrace();
        }
        return null;
    }
}
