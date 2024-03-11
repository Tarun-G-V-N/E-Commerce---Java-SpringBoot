package com.simplestore.UserService.controllers.controllerAdvise;

import com.simplestore.UserService.dtos.ErrorResponseDTO;
import com.simplestore.UserService.exceptions.NoValidSessionException;
import com.simplestore.UserService.exceptions.PasswordIncorrectException;
import com.simplestore.UserService.exceptions.SessionLimitExceededException;
import com.simplestore.UserService.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFoundException(Exception ex) {

        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setErrorMessage(ex.getMessage());
        responseDTO.setErrorCode(404);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<ErrorResponseDTO> hanndlePasswordIncorrectException(Exception ex) {

        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setErrorMessage(ex.getMessage());
        responseDTO.setErrorCode(400);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoValidSessionException.class)
    public ResponseEntity<ErrorResponseDTO> hanndleNoValidSession(Exception ex) {

        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setErrorMessage(ex.getMessage());
        responseDTO.setErrorCode(404);
        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SessionLimitExceededException.class)
    public ResponseEntity<ErrorResponseDTO> hanndleSessionLimitExceeded(Exception ex) {

        ErrorResponseDTO responseDTO = new ErrorResponseDTO();
        responseDTO.setErrorMessage(ex.getMessage());
        responseDTO.setErrorCode(403);
        return new ResponseEntity<>(responseDTO, HttpStatus.FORBIDDEN);
    }
}
