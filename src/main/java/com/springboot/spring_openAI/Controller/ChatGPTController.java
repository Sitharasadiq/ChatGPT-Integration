package com.springboot.spring_openAI.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.spring_openAI.Service.ChatGPTService;
import com.springboot.spring_openAI.dto.PromptRequest;

@RestController
@RequestMapping("/api/chat")

public class ChatGPTController {

	private final ChatGPTService chatGPTService;

	public ChatGPTController(ChatGPTService chatGPTService) {
		this.chatGPTService = chatGPTService;
	}

	@PostMapping
	public String chat(@RequestBody PromptRequest promptRequest) {
		return chatGPTService.getChatResponse(promptRequest);

	}

}
