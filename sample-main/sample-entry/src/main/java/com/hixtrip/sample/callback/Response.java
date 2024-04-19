package com.hixtrip.sample.callback;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Response {
    public static String prepareSuccessResponse(int orderId, BigDecimal amount) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("orderId", orderId);
            response.put("amount", amount);
            response.put("paymentStatus", "SUCCESS");
            response.put("paymentDetails", Map.of(
                    "paymentMethod", "CARD",
                    "transactionId", "ABCDE1234567890",
                    "authorizationCode", "123456"
            ));
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to generate JSON response", e);
        }
    }

    public static String prepareFailureResponse(int orderId, BigDecimal amount) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("orderId", orderId);
            response.put("amount", amount);
            response.put("paymentStatus", "FAILURE");
            response.put("errorMessage", "Insufficient funds");
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to generate JSON response", e);
        }
    }

    public static String prepareDuplicateResponse(int orderId, BigDecimal amount) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> response = new HashMap<>();
            response.put("orderId", orderId);
            response.put("amount", amount);
            response.put("paymentStatus", "DUPLICATE");
            response.put("message", "This payment has already been processed.");
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to generate JSON response", e);
        }
    }

}
