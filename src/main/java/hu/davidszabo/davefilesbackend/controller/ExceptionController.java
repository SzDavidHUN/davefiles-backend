package hu.davidszabo.davefilesbackend.controller;

import hu.davidszabo.davefilesbackend.exception.SimpleException;
import hu.davidszabo.davefilesbackend.exception.SimpleExceptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    private Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(SimpleException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public SimpleExceptionDTO SimpleExceptionHandler(SimpleException simpleException) {
        logger.info(simpleException.getErrorMessage() + " [" + simpleException.getSubject().toString() + "]");
        logger.debug(simpleException.getStackTrace().toString());
        return new SimpleExceptionDTO(simpleException);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        logger.info(e.toString() + " " + e.getMessage());
        logger.debug(e.getStackTrace().toString());
        return new SimpleExceptionDTO(400, e.toString(), e.getMessage());
    }

}
