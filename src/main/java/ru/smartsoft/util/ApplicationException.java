package ru.smartsoft.util;

/**
 * Базовый эксепшен.
 */
public class ApplicationException extends RuntimeException {

  private final String msg;

  public ApplicationException(String msg) {
    super(String.format("msgCode=%s", msg));
    this.msg = msg;
  }

  public String getMsg() {
    return msg;
  }

}
