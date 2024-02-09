package com.epam.httpClient;

public interface URIs {
    String MAIN_API = "http://localhost:8080/api/v1";
    String LOGIN = MAIN_API + "/auth/login";
    String CHANGE_LOGIN = MAIN_API + "/auth/change-login";
    String LOGOUT = MAIN_API + "/auth/logout";
    String TRAINEE = MAIN_API + "/trainees";
    String TRAINEE_TOGGLE_ACTIVE = MAIN_API + "/trainees/toggle-active";
    String TRAINER = MAIN_API + "/trainer";
    String TRAINER_TOGGLE_ACTIVE = MAIN_API + "/trainer/toggle-active";
    String TRAININGS = MAIN_API + "/trainings";
    String TRAINING_TYPES = MAIN_API + "/training-types";

}
