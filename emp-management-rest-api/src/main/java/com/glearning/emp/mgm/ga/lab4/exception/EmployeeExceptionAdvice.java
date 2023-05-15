package com.glearning.emp.mgm.ga.lab4.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is an advice class which is responsible to handle any kind of {@link RuntimeException} occurred in application.
 *
 * @author DIPANJAN DAS
 * @version 1.0
 * @since 15-May-2023
 */
@ControllerAdvice
@Slf4j
public class EmployeeExceptionAdvice {
    /**
     * This method is responsible to handle any kind of runtime exception in employee management application.
     *
     * @param ex Object of {@link Exception} class.
     * @return Exception message.
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String employeeNotFoundHandler(Exception ex) {
        log.error("Exception Occurred := ", ex);
        return ex.getMessage();
    }
}
