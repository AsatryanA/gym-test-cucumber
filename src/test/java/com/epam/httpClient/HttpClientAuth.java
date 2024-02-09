package com.epam.httpClient;

import com.epam.model.dto.request.ChangeLoginDTO;
import com.epam.model.dto.request.LoginDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.sl.In;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@RequiredArgsConstructor
public class HttpClientAuth {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public HttpResponse<?> login(LoginDTO loginDTO) throws IOException, InterruptedException {
        var loginDetails = objectMapper.writeValueAsString(loginDTO);
        var request = HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .uri(URI.create(URIs.LOGIN))
                .POST(HttpRequest.BodyPublishers.ofString(loginDetails))
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public   HttpResponse<?> changeLogin(ChangeLoginDTO changeLoginDTO) throws IOException, InterruptedException {
        var changeLoginDetails = objectMapper.writeValueAsString(changeLoginDTO);
        var request = HttpRequest.newBuilder()
                .uri(URI.create(URIs.CHANGE_LOGIN))
                .PUT(HttpRequest.BodyPublishers.ofString(changeLoginDetails))
                .header("Authorization", InMemoryToken.getToken())
                .header("Content-Type", "application/json")
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
