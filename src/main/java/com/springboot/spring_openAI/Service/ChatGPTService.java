package com.springboot.spring_openAI.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.springboot.spring_openAI.dto.ChatGPTResponse;
import com.springboot.spring_openAI.dto.PromptRequest;
import com.springboot.spring_openAI.dto.chatGPTRequest;

@Service
public class ChatGPTService {
	private final RestClient restClient;

	public ChatGPTService(RestClient restClient) {
		this.restClient = restClient;
	}

	@Value("${openapi.api.key}")
	private String apiKey;

	@Value("${openapi.model}")
	private String model;

	@Value("${openapi.api.url}")
	private String openAiUrl;

	public String getChatResponse(PromptRequest promptRequest) {
		chatGPTRequest cGptRequest = new chatGPTRequest(model,
				List.of(new chatGPTRequest.Message("user", promptRequest.prompt())));

		ChatGPTResponse response = restClient.post().uri(openAiUrl) // 👈 Add this line
				.header("Authorization", "Bearer " + apiKey) // Also added a space after 'Bearer'
				.header("Content-Type", "application/json").body(cGptRequest).retrieve().body(ChatGPTResponse.class);

		return response.choices().get(0).message().content();
	}
}
