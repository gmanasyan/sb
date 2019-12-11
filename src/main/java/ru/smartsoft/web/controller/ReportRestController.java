package ru.smartsoft.web.controller;

import static ru.smartsoft.model.converter.sbAdapter.responseItem;
import static ru.smartsoft.model.converter.sbAdapter.responsePurchaseList;
import static ru.smartsoft.model.converter.sbAdapter.responseUser;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.User;
import ru.smartsoft.model.sbxsd.SrvGetPurchaseItemRs;
import ru.smartsoft.model.sbxsd.SrvGetPurchaseListRs;
import ru.smartsoft.model.sbxsd.SrvGetUserRs;
import ru.smartsoft.repository.ItemRepo;
import ru.smartsoft.repository.PurchaseRepo;
import ru.smartsoft.repository.UserRepo;

/**
 * REST контроллер для отчетов.
 */
@RestController
@RequestMapping("/api/report")
public class ReportRestController extends ExceptionController {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  PurchaseRepo purchaseRepo;

  @Autowired
  ItemRepo itemRepo;

  @Autowired
  UserRepo userRepo;

  @Operation(summary = "List of purchases by last week", tags = {"Reports"}, responses = {
      @ApiResponse(responseCode = "200", description = "List of purchases",
          content = @Content(mediaType = "application/xml",
              schema = @Schema(implementation = SrvGetPurchaseListRs.class))),
      @ApiResponse(responseCode = "204", content = @Content(schema = @Schema()),
          description = "Purchases not found"),
      @ApiResponse(responseCode = "401", content = @Content(schema = @Schema()),
          description = "You are not authorized to view the resource")
  })
  @RequestMapping(value = "/week", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String purchases(Model model) {
    log.info("Week");
    List purchases = purchaseRepo.getAllLastWeek();
    checkIfNull(purchases, "Нет покупок за последнюю неделю");

    return jaxbConverter.getXml(responsePurchaseList(purchases));
  }

  @Operation(summary = "Popular product of last month", tags = {"Reports"}, responses = {
      @ApiResponse(responseCode = "200", description = "List of purchases",
          content = @Content(mediaType = "application/xml",
              schema = @Schema(implementation = SrvGetPurchaseItemRs.class))),
      @ApiResponse(responseCode = "204", content = @Content(schema = @Schema()),
          description = "Purchases not found"),
      @ApiResponse(responseCode = "401", content = @Content(schema = @Schema()),
          description = "You are not authorized to view the resource")
  })
  @RequestMapping(value = "/bestseller/month", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String bestsellerOfMonth(Model model) {
    log.info("Bestseller of month");
    Item result = itemRepo.getBestsellerOfMonth();
    checkIfNull(result, "Нет покупок за последний месяц");

    return jaxbConverter.getXml(
        responseItem(result, "Самый покупаемый товар за последний месяц"));
  }

  @Operation(summary = "Best buyer of last 6 months", tags = {"Reports"}, responses = {
      @ApiResponse(responseCode = "200", description = "List of purchases",
          content = @Content(mediaType = "application/xml",
              schema = @Schema(implementation = SrvGetUserRs.class))),
      @ApiResponse(responseCode = "204", content = @Content(schema = @Schema()),
          description = "Purchases not found"),
      @ApiResponse(responseCode = "401", content = @Content(schema = @Schema()),
          description = "You are not authorized to view the resource")
  })
  @RequestMapping(value = "/bestbuyer/halfyear", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String bestbuyerOfHalfYear(Model model) {
    log.info("Bestbuyer of halfyear");
    User result = userRepo.getBestsellerOfHalthYear();
    checkIfNull(result, "Нет покупок за последние полгода");

    String description = "Человек совершивший больше всего покупок (по количеству) за полгода";
    return jaxbConverter.getXml(responseUser(result, description));
  }

  @Operation(summary = "Popular product for people if given age", tags = {"Reports"}, responses = {
      @ApiResponse(responseCode = "200", description = "List of purchases",
          content = @Content(mediaType = "application/xml",
              schema = @Schema(implementation = SrvGetPurchaseItemRs.class))),
      @ApiResponse(responseCode = "204", content = @Content(schema = @Schema()),
          description = "Purchases not found"),
      @ApiResponse(responseCode = "401", content = @Content(schema = @Schema()),
          description = "You are not authorized to view the resource")
  })
  @RequestMapping(value = "/bestseller/{age}", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String bestsellerByAge(@PathVariable("age") Integer age, Model model) {
    log.info("Bestseller by age {}", age);
    Item result = itemRepo.getBestsellerOfAge(age);
    checkIfNull(result, "Нет товара купленного людьми в возрасте " + age);

    String description = "Самый покупаемый товар для людей в возрасте " + age;
    return jaxbConverter.getXml(responseItem(result, description));
  }

}
