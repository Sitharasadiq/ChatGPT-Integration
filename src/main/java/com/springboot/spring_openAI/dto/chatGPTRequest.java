package com.springboot.spring_openAI.dto;

import java.util.List;

public record chatGPTRequest(String model, List<Message> messages) {

	public static record Message(String role, String content) {

	}

}
