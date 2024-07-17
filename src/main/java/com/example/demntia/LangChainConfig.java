package com.example.demntia;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.openai.OpenAiChatModel;
import jakarta.annotation.PostConstruct;

@Configuration
public class LangChainConfig {

	@Value("${openai.api.key}")
	private String openaiApiKey;

	@Bean
	public OpenAiChatModel openAiChatModel() {
		return OpenAiChatModel.builder().apiKey(openaiApiKey).modelName("gpt-4").temperature(0.0).build();
	}

	@PostConstruct
	public void load() throws IOException {
		AIContextDataLoader contextDataLoader = new AIContextDataLoader();
		System.out.println("The context size :" + contextDataLoader.size());
	}
}
