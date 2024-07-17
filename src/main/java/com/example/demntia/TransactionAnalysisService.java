package com.example.demntia;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.langchain4j.model.chat.ChatLanguageModel;

@Service
public class TransactionAnalysisService {

	@Autowired
	private ChatLanguageModel chatLanguageModel;

	private static final String GREP_TXT = "I'm sorry";

	public String analyzeTransactions(String query) throws IOException {
		String prompt = "Understand the query and Identify " + query
				+ "respond in html with bold and highlight with keys words, if unsure say I don't know; Ask something.:\n\n"
				+ "TRANSACTION DATA " + AIContextDataLoader.get(AIContextDataLoader.TRANS)
				+ "RULES OR RESTRICTION OF DATA " + AIContextDataLoader.get(AIContextDataLoader.RULES)
				+ "CARE TAKERS DATA " + AIContextDataLoader.get(AIContextDataLoader.CARETAKER);
		return wrapIt(chatLanguageModel.generate(prompt));
	}

	private static String wrapIt(final String response) {

		return response.contains(GREP_TXT) ? "I didn't undesrand; Ask something." : response;
	}
}
