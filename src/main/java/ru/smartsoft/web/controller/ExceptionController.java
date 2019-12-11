package ru.smartsoft.web.controller;

import static ru.smartsoft.model.converter.sbAdapter.responsePurchase;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.smartsoft.model.converter.JaxbConverter;
import ru.smartsoft.model.sbxsd.ErrorInfoRs;
import ru.smartsoft.util.ApplicationException;
import ru.smartsoft.util.JaxbException;
import ru.smartsoft.util.NotFoundException;

/**
 * Исключения для контроллеров.
 */
public class ExceptionController {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  protected final JaxbConverter jaxbConverter =
      new JaxbConverter("SrvCreatePurchaseRq", "xsd/sb_scheme.xsd");

  // https://stackoverflow.com/questions/26845631/is-it-correct-to-return-404-when-a-rest-resource-is-not-found/26845858
  @ResponseStatus(HttpStatus.NO_CONTENT)  //204 - server side
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> notFound(HttpServletRequest req, ApplicationException e) throws Exception {
    return ResponseEntity.noContent().build();
  }

  @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)  // 422 - client side problem
  @ExceptionHandler(JaxbException.class)
  public ResponseEntity<String> wrongRequest(HttpServletRequest req, JaxbException e) throws Exception {
    ErrorInfoRs errorInfoRs = new ErrorInfoRs();
    errorInfoRs.setUrl(req.getRequestURI());
    errorInfoRs.setErrorMessage(e.getMsg());
    return ResponseEntity.unprocessableEntity().body(jaxbConverter.getXml(errorInfoRs));
  }

  //******** Methods *******

  protected void checkIfNull(Object object, String msg) {
    if (object == null)  {
      log.info("Ошибка: {}", msg);
      throw new NotFoundException(msg);
    }
  }
}
