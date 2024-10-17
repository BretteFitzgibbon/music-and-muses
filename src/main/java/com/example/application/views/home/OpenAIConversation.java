package com.example.application.views.home;

package ai.peoplecode;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.output.Response;
import java.util.ArrayList;
import java.util.List;

public class OpenAIConversation {
    private MessageWindowChatMemory chatMemory;
    private ChatLanguageModel chatModel;

    public OpenAIConversation() {
        this("demo", "gpt-3.5-turbo");
    }

    public OpenAIConversation(String apiKey) {
        this(apiKey, "gpt-3.5-turbo");
    }

    public OpenAIConversation(String apiKey, String modelName) {
        this.chatModel = OpenAiChatModel.builder().apiKey(apiKey).modelName(modelName).build();
        this.chatMemory = MessageWindowChatMemory.withMaxMessages(10);
    }

    public String askQuestion(String context, String question) {
        SystemMessage sysMessage = SystemMessage.from(context);
        this.chatMemory.add(sysMessage);
        UserMessage userMessage = UserMessage.from(question);
        this.chatMemory.add(userMessage);
        Response response = this.chatModel.generate(this.chatMemory.messages());
        AiMessage aiMessage = (AiMessage)response.content();
        this.chatMemory.add(aiMessage);
        String responseText = aiMessage.text();
        return responseText;
    }

    public List<String> generateSampleQuestions(String context, int count, int maxWords) {
        new ArrayList();
        String instructions = "For the context following, please provide a list of " + count + " questions with a maximum of " + maxWords + " words per question.";
        instructions = instructions + " Return the questions as a string with delimiter '%%' between each generated question";
        SystemMessage sysMessage = SystemMessage.from(instructions);
        UserMessage userMessage = UserMessage.from(context);
        List<ChatMessage> prompt = new ArrayList();
        prompt.add(sysMessage);
        prompt.add(userMessage);
        Response response = this.chatModel.generate(prompt);
        AiMessage aiMessage = (AiMessage)response.content();
        String responseText = aiMessage.text();
        String[] questionArray = responseText.split("%%");
        return List.of(questionArray);
    }

    public void resetConversation() {
        this.chatMemory.clear();
    }

    public String toString() {
        return this.chatMemory.messages().toString();
    }

    public static void main(String[] args) {
        OpenAIConversation conversation = new OpenAIConversation("demo");
        List<String> questions = conversation.generateSampleQuestions("Questions about films in the 1960s", 3, 10);
        System.out.println("Sample questions: " + String.valueOf(questions));
        String response = conversation.askQuestion("You are a film expert", "What are the three best Quentin Tarintino movies?");
        System.out.println("Response: " + response);
        response = conversation.askQuestion("You are a film expert", "How old is he");
        System.out.println("Response: " + response);
        System.out.println("\nConversation History:");
        System.out.println(conversation);
    }
}
