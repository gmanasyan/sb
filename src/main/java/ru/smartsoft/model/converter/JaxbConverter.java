package ru.smartsoft.model.converter;


import java.io.File;
import java.io.StringReader;
import java.net.URL;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import ru.smartsoft.model.Purchase;
import ru.smartsoft.model.sbxsd.SrvCreatePurchaseRq;
import ru.smartsoft.util.JaxbException;

/**
 * Конвертер входящих данных
 */
public class JaxbConverter {

  protected final Logger log = LoggerFactory.getLogger(getClass());

  private final String contextClass;
  private final String validationScheme;

  public JaxbConverter(String contextClass, String validationScheme) {
    this.contextClass = contextClass;
    this.validationScheme = validationScheme;
  }

  public SrvCreatePurchaseRq getObject(String xml) {

    URL fileUrl = getClass().getClassLoader().getResource(validationScheme);
    JAXBContext jaxbContext;
    try
    {
      jaxbContext = JAXBContext.newInstance(SrvCreatePurchaseRq.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      //Setup schema validator
      SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema itemSchema = sf.newSchema(new File(fileUrl.getFile()));
      jaxbUnmarshaller.setSchema(itemSchema);

      //Unmarshal xml file
      SrvCreatePurchaseRq srvCreatePurchaseRq = (SrvCreatePurchaseRq) jaxbUnmarshaller
          .unmarshal(new StringReader(xml));
      return srvCreatePurchaseRq;
    }
    catch (JAXBException | SAXException e)
    {
        log.error("JAXB error {}", e.getCause().getMessage());
        throw new JaxbException(e.getCause().getMessage());
    }
  }

}
