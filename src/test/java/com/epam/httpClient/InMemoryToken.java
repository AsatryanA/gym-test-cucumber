package com.epam.httpClient;

public class InMemoryToken {
    private static String token;

    public boolean isValidToken() {
        return token.isEmpty() || token.isBlank();
    }

    public static void setToken(String token) {
        InMemoryToken.token = "Bearer "+token;
    }

    public static String getToken() {
        return token;
    }
}
