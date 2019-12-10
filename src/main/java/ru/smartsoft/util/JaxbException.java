package ru.smartsoft.util;

/**
 * Эксепшен при работе JAXB.
 */
public class JaxbException extends RuntimeException {

  private final String msg;

  public JaxbException(String msg) {
    super(String.format("msgCode=%s", msg));
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }
}
