package ru.smartsoft.web;

import org.springframework.web.bind.annotation.RestControllerAdvice;

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
