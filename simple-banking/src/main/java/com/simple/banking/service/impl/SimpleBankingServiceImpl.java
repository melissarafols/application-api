package com.simple.banking.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.banking.model.AccountDebitCreditTransaction;
import com.simple.banking.model.AccountHolder;
import com.simple.banking.model.AccountTransactionFormatted;
import com.simple.banking.model.AccountTransactionHistory;
import com.simple.banking.model.MessageResponse;
import com.simple.banking.service.SimpleBankingService;

import reactor.core.publisher.Mono;

@Service
public class SimpleBankingServiceImpl implements SimpleBankingService {
	@Value("${baseUr}")
	private String baseUrl;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Override
	public AccountHolder saveAccount(AccountHolder holder) {
		String baseUrlSave = baseUrl + "account";
		MessageResponse response = webClientBuilder.build().post().uri(baseUrlSave).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).bodyValue(holder).retrieve().bodyToMono(MessageResponse.class)
				.block();
		ObjectMapper mapper = new ObjectMapper();
		AccountHolder newAcct = mapper.convertValue(response.getResult(), AccountHolder.class);

		return newAcct;
	}

	@Override
	public AccountHolder getAccountDetails(String accountPin) {
		String baseUrlGetDetails = baseUrl + "account/" + accountPin;
		Mono<Object> response = webClientBuilder.build().get().uri(baseUrlGetDetails).accept(MediaType.APPLICATION_JSON)
				.exchange().flatMap(clientResponse -> {
					if (clientResponse.statusCode().is5xxServerError()) {
						clientResponse.body((clientHttpResponse, context) -> {
							return clientHttpResponse.getBody();
						});
						return clientResponse.bodyToMono(MessageResponse.class);
					} else
						return clientResponse.bodyToMono(MessageResponse.class);
				});
		if (response.block().toString().contains("error")) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		AccountHolder acctDtl = mapper.convertValue(((MessageResponse) response.block()).getResult(),
				AccountHolder.class);
		return acctDtl;
	}

	@Override
	public AccountHolder saveAccountTransaction(AccountDebitCreditTransaction debitCreditTransaction) {
		String baseUrlSave = baseUrl + "transaction";
		MessageResponse response = webClientBuilder.build().post().uri(baseUrlSave).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).bodyValue(debitCreditTransaction).retrieve()
				.bodyToMono(MessageResponse.class).block();
		ObjectMapper mapper = new ObjectMapper();
		AccountHolder updatedAcct = mapper.convertValue(response.getResult(), AccountHolder.class);
		return updatedAcct;
	}

	@Override
	public List<AccountTransactionFormatted> getTransactionHistory(String accountPin) {
		String baseUrlListTransactions = baseUrl + "transaction-history/" + accountPin;
		AccountTransactionHistory[] response = webClientBuilder.build().get().uri(baseUrlListTransactions)
				.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(AccountTransactionHistory[].class).block();
		
		List<AccountTransactionHistory> historyList = Arrays.asList(response);
		List<AccountTransactionFormatted> formatteds = new ArrayList<>();
		int sequence = historyList.size();
		for (AccountTransactionHistory history : historyList) {
			AccountTransactionFormatted newDtl = new AccountTransactionFormatted();
			newDtl.setId(sequence);
			if (history.getTransactionType().equalsIgnoreCase("CREDIT")) {
				newDtl.setDebitAmount(BigDecimal.valueOf(0.00));
				newDtl.setCreditAmount(history.getAmount());
			} else {
				newDtl.setDebitAmount(history.getAmount());
				newDtl.setCreditAmount(BigDecimal.valueOf(0.00));
			}
			newDtl.setRunningBalance(history.getRunningBalance());
			String formattedDate = new SimpleDateFormat("MM/dd/yyyy hh:mm").format(history.getDateTime());
			newDtl.setDateTime(formattedDate);
			formatteds.add(newDtl);
			sequence--;
		}

		return formatteds;
	}

}
