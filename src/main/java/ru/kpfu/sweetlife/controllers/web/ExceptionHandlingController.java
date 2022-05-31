package ru.kpfu.sweetlife.controllers.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kpfu.sweetlife.exceptions.ApplicationException;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ApplicationException.class)
    public void handleControllerException(ApplicationException e) {
        log.warn(getExceptionMessage(e));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        log.error(getExceptionMessage(e));
        return "error/serverErrorPage";
    }

    private String getExceptionMessage(Exception e) {
        return String.format("Exception '%s' caused by %s", e.getMessage(), e.toString());
    }
}
