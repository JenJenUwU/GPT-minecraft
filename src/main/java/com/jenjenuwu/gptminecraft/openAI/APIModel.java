package com.jenjenuwu.gptminecraft.openAI;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest.ChatCompletionRequestBuilder;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
public class APIModel {
    private OpenAiService service = new OpenAiService("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");
    private ChatCompletionRequestBuilder requestBuilder(){
        return ChatCompletionRequest.builder()
                .model("gpt-4")
                .maxTokens(1000);
    }
    public String getResponse(String Prompt){
        ChatCompletionRequest = requestBuilder()
    }
}
