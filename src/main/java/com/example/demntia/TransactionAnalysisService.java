package com.example.demntia;

import dev.langchain4j.model.chat.ChatLanguageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class TransactionAnalysisService {

    @Autowired
    private ChatLanguageModel chatLanguageModel;

    public String analyzeTransactions(String query) throws IOException {
        String transactionHistory = readTransactionHistory();
        String prompt = "Analyze the following transaction history. " +
                "Identify" + query + 
                "Provide a summary of your findings:\n\n" +
                transactionHistory;
        return chatLanguageModel.generate(prompt);
    }

    private String readTransactionHistory() throws IOException {
        ClassPathResource resource = new ClassPathResource("transaction_history.txt");
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }
}
