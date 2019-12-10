package ru.smartsoft.web.controller;

import static ru.smartsoft.model.converter.sbAdapter.responsePurchase;
import static ru.smartsoft.model.converter.sbAdapter.responsePurchaseList;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import javax.xml.bind.JAXBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.Purchase;
import ru.smartsoft.model.User;
import ru.smartsoft.model.converter.JaxbConverter;
import ru.smartsoft.model.converter.sbAdapter;
import ru.smartsoft.model.sbxsd.SrvCreatePurchaseRq;
import ru.smartsoft.model.sbxsd.SrvGetPurchaseListRs;
import ru.smartsoft.model.sbxsd.SrvGetPurchaseRs;
import ru.smartsoft.repository.ItemRepo;
import ru.smartsoft.repository.PurchaseRepo;
import ru.smartsoft.repository.UserRepo;
import ru.smartsoft.util.ErrorInfo;

/**
 * REST контроллер для работы с покупками.
 */
@OpenAPIDefinition(
    info = @Info(
        title = "The purchase controller",
        version = "0.5",
        description = "My API"
    ),
    tags = {
    @Tag(name = "Purchases", description = "Purchases Operations"),
    @Tag(name = "Reports", description = "Reports Operations")
}
)
@RestController
@RequestMapping(value = PurchaseRestController.REST_URL)
public class PurchaseRestController extends ExceptionController {

  static final String REST_URL = "/api/purchases";

  protected final Logger log = LoggerFactory.getLogger(getClass());

  private final JaxbConverter jaxbConverter =
      new JaxbConverter("SrvCreatePurchaseRq", "xsd/sb_scheme.xsd");

  @Autowired
  PurchaseRepo purchaseRepo;

  @Autowired
  ItemRepo itemRepo;

  @Autowired
  UserRepo userRepo;

  /**
   * Все покупки отсортированные по дате по убыванию.
   */
  @Operation(summary = "List of purchases by date", tags = {"Purchases"}, responses = {
      @ApiResponse(responseCode = "200", description = "List of purchases by date",
          content = @Content(mediaType = "application/xml",
              schema = @Schema(implementation = SrvGetPurchaseListRs.class))),
      @ApiResponse(responseCode = "401", content = @Content(schema = @Schema()),
          description = "You are not authorized to view the resource")
  })
  @RequestMapping(value = "/", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public String purchases() throws JAXBException {
    log.info("Purchases");
    List<Purchase> purchases = purchaseRepo.getAll();
    return jaxbConverter.getXml(responsePurchaseList(purchases));
  }

  /**
   * Запрос покупки по номеру.
   */
  @Operation(summary = "Get purchases by id", tags = {"Purchases"}, responses = {
      @ApiResponse(responseCode = "200", description = "Requested purchase",
          content = @Content(mediaType = "application/xml",
              schema = @Schema(implementation = SrvGetPurchaseRs.class))),
      @ApiResponse(responseCode = "401", content = @Content(schema = @Schema()),
          description = "You are not authorized to view the resource"),
      @ApiResponse(responseCode = "404", description = "Purchase by this id not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorInfo.class)))
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.GET,
      produces = { MediaType.APPLICATION_XML_VALUE })
  public ResponseEntity<String> getOne(@PathVariable int id) throws JAXBException {
    log.info("Get purchase with id: {}", id);
    Purchase purchase = purchaseRepo.findById(id).orElse(null);
    checkIfNull(purchase, "Нет покупки с номером " + id);
    return ResponseEntity.ok().body(jaxbConverter.getXml(responsePurchase(purchase)));
  }

  /**
   * Создание покупки.
   */
  @Operation(summary = "Create purchase", tags = {"Purchases"}, responses = {
      @ApiResponse(responseCode = "201", content = @Content(schema = @Schema()),
          description = "Created successfully"),
      @ApiResponse(responseCode = "401", content = @Content(schema = @Schema()),
          description = "You are not authorized to view the resource"),
      @ApiResponse(responseCode = "422", description = "XML validation error",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ErrorInfo.class)
          )
      )
  })
  @RequestMapping(value = "/", method = RequestMethod.POST,
      headers = {"content-type=application/xml"})
  @ResponseStatus(value = HttpStatus.CREATED)
  public ResponseEntity<String> createItem(
      @RequestBody(description = "Created purchase",
          required = true,
          content = @Content(mediaType = "application/xml",
          schema = @Schema(implementation = SrvCreatePurchaseRq.class)))
          String request) {
    request = java.net.URLDecoder.decode(request, StandardCharsets.UTF_8);
    log.info("Create: {}" + request);
    Purchase purchase = sbAdapter.convert(jaxbConverter.getObject(request));
    Purchase created = savePurchase(purchase);

    URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
        .path(REST_URL + "/{id}")
        .buildAndExpand(created.getId()).toUri();
    return ResponseEntity.created(uriOfNewResource).body("");
  }

  /**
   * Апдейт покупки.
   */
  @Operation(summary = "Update purchase", tags = {"Purchases"}, responses = {
      @ApiResponse(responseCode = "201", description = "Created successfully",
          content = @Content(mediaType = "application/text")),
      @ApiResponse(responseCode = "401", content = @Content(schema = @Schema()),
          description = "You are not authorized to view the resource"),
      @ApiResponse(responseCode = "404", description = "Purchase by this id not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorInfo.class))),
      @ApiResponse(responseCode = "422", description = "XML validation error",
          content = @Content(
              mediaType = "application/json",
              schema = @Schema(implementation = ErrorInfo.class)
          )
      )
  })
  @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
      headers = {"content-type=application/xml"})
  public ResponseEntity<String> update(@PathVariable int id,
      @RequestBody(description = "Update purchase",
          required = true,
          content = @Content(mediaType = "application/xml",
              schema = @Schema(implementation = SrvCreatePurchaseRq.class)))
      String request) {
    request = java.net.URLDecoder.decode(request, StandardCharsets.UTF_8);
    log.info("Update: {}" + request);
    Purchase existedPurchase = purchaseRepo.findById(id).orElse(null);
    checkIfNull(existedPurchase, "Нет покупки с номером " + id);

    Purchase purchase = sbAdapter.convert(jaxbConverter.getObject(request));
    purchase.setId(id);
    savePurchase(purchase);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  /**
   * Удаление покупки.
   */
  @Operation(summary = "Delete purchase", tags = {"Purchases"}, responses = {
      @ApiResponse(responseCode = "204", content = @Content(schema = @Schema()),
          description = "Delete completed"),
      @ApiResponse(responseCode = "401", content = @Content(schema = @Schema()),
          description = "You are not authorized to view the resource"),
      @ApiResponse(responseCode = "404", description = "Purchase by this id not found",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = ErrorInfo.class)))
  })
  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    log.info("Delete purchase with id {}", id);
    Purchase existedPurchase = purchaseRepo.findById(id).orElse(null);
    checkIfNull(existedPurchase, "Нет покупки с номером " + id);
    purchaseRepo.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  /**
   * Метод для создания и апдейта покупки.
   */
  private Purchase savePurchase(Purchase purchase) {
    Item item = itemRepo.findByName(purchase.getItem().getName());
    if (item != null) {
      purchase.setItem(item);
    } else {
      itemRepo.save(purchase.getItem());
    }

    User user = userRepo.findByAllFields(
        purchase.getUser().getName(),
        purchase.getUser().getLastName(),
        purchase.getUser().getAge()
    );
    if (user != null) {
      purchase.setUser(user);
    } else {
      userRepo.save(purchase.getUser());
    }
    return purchaseRepo.save(purchase);
  }



}
