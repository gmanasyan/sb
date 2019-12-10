package ru.smartsoft.util;

/**
 * Класс для возвращения ошибки клиенту при обработке эксепшенов.
 */
public class ErrorInfo {
  private String url;
  private String errorMessage;

  public ErrorInfo(String url, String errorMessage) {
    this.url = url;
    this.errorMessage = errorMessage;
  }

  public String getUrl() {
    return url;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
