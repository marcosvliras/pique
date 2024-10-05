package com.pique.pique.domain.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pique.pique.domain.dtos.transaction.TransactionDTO;
import com.pique.pique.domain.exceptions.InvalidTransactionException;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

@Service
public class TransactionValidatorService {

    public void validate(TransactionDTO transactionDTO) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://util.devi.tools/api/v2/authorize"))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        responseFuture.thenApply(HttpResponse::body)
                .thenAccept(body -> {
                    ObjectMapper mapper = new ObjectMapper();
                    try {
                        JsonNode jsonNode = mapper.readTree(body);
                        String status = jsonNode.get("status").asText();

                        if (!"success".equals(status)) {
                            throw new InvalidTransactionException("Error with the transaction validation.");
                        }
                    } catch (Exception e) {
                        throw new InvalidTransactionException("Error with the transaction validation.");
                    }
                })
                .exceptionally(ex -> {
                    throw new InvalidTransactionException("Error with the transaction validation.");
                }).join();
    }


}
