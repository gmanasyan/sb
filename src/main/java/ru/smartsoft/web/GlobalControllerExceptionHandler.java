package ru.smartsoft.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.smartsoft.util.JaxbException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

  //@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422
  @ExceptionHandler(JaxbException.class)
  public ModelAndView wrongRequest(HttpServletRequest req, JaxbException e) throws Exception {
    ModelAndView mav = new ModelAndView("error");
    mav.addObject("errorMessage", e.getMsg());
    return mav;
  }

}
