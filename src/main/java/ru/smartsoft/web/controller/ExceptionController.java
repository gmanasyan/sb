package ru.smartsoft.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.smartsoft.util.ApplicationException;
import ru.smartsoft.util.ErrorInfo;
import ru.smartsoft.util.JaxbException;
import ru.smartsoft.util.NotFoundException;

/**
 * Исключения для контроллеров.
 */
public class ExceptionController {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  @ResponseStatus(HttpStatus.NOT_FOUND)  //404
  @ExceptionHandler(NotFoundException.class)
  public ErrorInfo applicationError(HttpServletRequest req, ApplicationException e) throws Exception {
    return new ErrorInfo(req.getRequestURI(), e.getMsg());
  }

  @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
  @ExceptionHandler(JaxbException.class)
  public ErrorInfo wrongRequest(HttpServletRequest req, JaxbException e) throws Exception {
    return new ErrorInfo(req.getRequestURI(), e.getMsg());
  }

  //******** Methods *******

  protected void checkIfNull(Object object, String msg) {
    if (object == null)  {
      log.info("Ошибка: {}", msg);
      throw new NotFoundException(msg);
    }
  }
}
