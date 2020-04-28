package com.mindera.school.multiplayer.http.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mindera.school.multiplayer.exceptions.LobbyDoesntExist;
import com.mindera.school.multiplayer.exceptions.UserDoesntExist;
import com.mindera.school.multiplayer.http.models.APIError;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserDoesntExist.class)
    public APIError userNotFound(UserDoesntExist e) {
        return new APIError("USER NOT FOUND", e.getMessage());
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(LobbyDoesntExist.class)
    public APIError lobbyNotFound(LobbyDoesntExist e) {
        return new APIError("LOBBY NOT FOUND", e.getMessage());
    }
}
