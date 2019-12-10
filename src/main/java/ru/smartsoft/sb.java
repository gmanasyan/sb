package ru.smartsoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Спринг Бут приложение.
 * - Можно запускать как отдельное приложение, включает встроенный сервер.
 * - Также можно деплоить на отдельный сервер.
 */
@SpringBootApplication
public class sb extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(sb.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(sb.class, args);
  }

}
