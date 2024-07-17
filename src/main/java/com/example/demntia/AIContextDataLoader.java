package com.example.demntia;

import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.core.io.ClassPathResource;

public class AIContextDataLoader {

	public static final String TRANS = "TRANSACTIONS";
	public static final String RULES = "GROUNDRULES";
	public static final String CARETAKER = "CARETAKERS";

	private static final ConcurrentHashMap<String, String> AI_CONTEXT = new ConcurrentHashMap<>();

	public AIContextDataLoader() throws IOException {
		AI_CONTEXT.put(TRANS, readTransactionsData());
		AI_CONTEXT.put(RULES, readRulesData());
		AI_CONTEXT.put(CARETAKER, readCaretakersData());
	}

	public int size() {
		return AI_CONTEXT.size();
	}

	public static String get(final String key) {
		return AI_CONTEXT.get(key);
	}

	public static boolean addTransaction(final String data) {
		try {
			AI_CONTEXT.put(TRANS, AI_CONTEXT.get(TRANS).concat(data + "\n"));
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to add a given transaction.");
			return false;
		}
	}

	private static String readTransactionsData() throws IOException {
		return read("transaction_history.txt");
	}

	private static String readCaretakersData() throws IOException {
		return read("care-takers-data.txt");
	}

	private static String readRulesData() throws IOException {
		return read("trans-ground-rules.txt");
	}

	private static String read(final String fileName) throws IOException {
		ClassPathResource resource = new ClassPathResource(fileName);
		return new String(Files.readAllBytes(resource.getFile().toPath()));
	}

}
