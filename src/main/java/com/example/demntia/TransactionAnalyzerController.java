package com.example.demntia;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionAnalyzerController {
	@Autowired
	private TransactionAnalysisService transactionAnalysisService;
	@Autowired
	private CareTakerRepository careTakerRepository;

	@GetMapping("/analyze")
	public String analyzeTransactions(@RequestParam String query) throws IOException {
		return transactionAnalysisService.analyzeTransactions(query);
	}

	@GetMapping("/careTakers")
	public List<CareTakerEntity> getAllCareTaker() {
		return careTakerRepository.findAll();
	}

	@GetMapping("/addTrans")
	public boolean addTransaction(@RequestParam String data) {
		return AIContextDataLoader.addTransaction(data);
	}
}