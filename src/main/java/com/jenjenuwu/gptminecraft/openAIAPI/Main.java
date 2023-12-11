package com.jenjenuwu.gptminecraft.openAIAPI;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String apiKey = System.getenv("OPENAI_API_KEY");
        // Replace "your-api-key" with your actual OpenAI API key
        System.out.println("API Key: " + apiKey);
        OpenAiService service = new OpenAiService("your-api-key");

        // Build a simple chat completion request
        List<ChatMessage> messages = Collections.singletonList(new ChatMessage("user", "Hey there, how are you?"));
        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-4")
                .messages(messages)
                .build();

        // Get the response from the OpenAI service
        try {
            String response = service.createChatCompletion(request).getChoices().get(0).getMessage().getContent().trim();

            // Print the response (for demonstration purposes)
            System.out.println("Response: " + response);
        } catch (Exception e) {
            // Handle exceptions appropriately in your application
            e.printStackTrace();
        }
    }
}
