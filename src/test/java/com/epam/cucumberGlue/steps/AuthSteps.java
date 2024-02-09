package com.epam.cucumberGlue.steps;

import com.epam.httpClient.HttpClientAuth;
import com.epam.httpClient.InMemoryToken;
import com.epam.model.dto.request.ChangeLoginDTO;
import com.epam.model.dto.request.LoginDTO;
import com.epam.model.dto.response.LoginResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RequiredArgsConstructor
public class AuthSteps {

    private final HttpClientAuth httpClientAuth;
    private final ObjectMapper objectMapper;
    private LoginDTO loginDTO;
    private ChangeLoginDTO changeLoginDTO;
    private HttpResponse<?> response;
    private ResponseEntity<Void> changeLoginResponse;

    @Given("a user with username {string} and password {string}")
    public void aUserWithUsernameAndPassword(String username, String password) {
        loginDTO = new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);
    }

    @When("the user logs in")
    public void theUserLogsIn() throws IOException, InterruptedException {
        response = httpClientAuth.login(loginDTO);
    }

    @Then("the user receives a valid access token")
    public void theUserReceivesAValidAccessToken() throws JsonProcessingException {
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.statusCode());
        assertNotNull(response.body());
        var body = (String) response.body();
        var loginResponseDTO = objectMapper.readValue(body, LoginResponseDTO.class);
        assertEquals("Bearer ", loginResponseDTO.getTokenType());
        InMemoryToken.setToken(loginResponseDTO.getToken());
    }

    @Then("the login fails with status code {int}")
    public void theLoginFailsWithStatusCode(int expectedStatusCode) {
        assertNotNull(response);
        assertEquals(expectedStatusCode, response.statusCode());
    }

    @Given("a user with ID {string} and old password {string} and new password {string}")
    public void aUserWithIdAndOldPasswordAndNewPassword(String userId, String oldPassword, String newPassword) {
        changeLoginDTO = new ChangeLoginDTO();
        changeLoginDTO.setId(Long.parseLong(userId));
        changeLoginDTO.setOldPassword(oldPassword);
        changeLoginDTO.setNewPassword(newPassword);
    }

    @When("the user changes the login password")
    public void theUserChangesTheLoginPassword() throws IOException, InterruptedException {
        response = httpClientAuth.changeLogin(changeLoginDTO);
    }

    @Then("the password is updated successfully")
    public void thePasswordIsUpdatedSuccessfully() {
        assertNotNull(response);
        assertEquals(HttpStatus.OK.value(), response.statusCode());
    }

    @Then("the password change fails with status code {int}")
    public void thePasswordChangeFailsWithStatusCode(int expectedStatusCode) {
        assertNotNull(response);
        assertEquals(expectedStatusCode, response.statusCode());
    }
}
