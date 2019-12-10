package ru.smartsoft.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import ru.smartsoft.util.ApplicationException;
import ru.smartsoft.util.ErrorInfo;
import ru.smartsoft.util.JaxbException;

/**
 * Глобальный обработчик всех эксепшенов.
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {

//  @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
//  @ExceptionHandler(JaxbException.class)
//  public ErrorInfo wrongRequest(HttpServletRequest req, JaxbException e) throws Exception {
//    return new ErrorInfo(req.getRequestURI(), e.getMsg());
//  }
//
//  @ResponseStatus(HttpStatus.NOT_FOUND)  //404
//  @ExceptionHandler(ApplicationException.class)
//  public ErrorInfo applicationError(HttpServletRequest req, ApplicationException e) throws Exception {
//    return new ErrorInfo(req.getRequestURI(), e.getMsg());
//  }

}
