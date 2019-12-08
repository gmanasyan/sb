package ru.smartsoft;

import java.io.File;
import java.io.StringReader;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;
import ru.smartsoft.model.Item;
import ru.smartsoft.model.sbxsd.SrvCreatePurchaseRq;

public class XmlTest {

  public static void main(String[] args) {

    validateXML();

    String xmlString = "<item>" +
        "    <id>345</id>" +
        "    <name>Tv</name>" +
        "    <cost>1</cost>" +
        "</item>";

    JAXBContext jaxbContext;
    try {
      jaxbContext = JAXBContext.newInstance(Item.class);

      Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
      Object item = unmarshaller.unmarshal(new StringReader(xmlString));
      System.out.println(item);
    } catch (JAXBException e) {
      e.printStackTrace();
    }

  }


  private static void validateXML() {

    String xmlString = "<SrvCreatePurchaseRq>" +
        "    <name>Ivan</name>" +
        "    <lastName>Ivanov</lastName>" +
        "    <age>33</age>" +
        "    <purchase_item>Tv</purchase_item>" +
        "    <count>1</count>" +
        "    <amount>3</amount>" +
        "    <purchaseDate>2019-12-08T12:00:00</purchaseDate>" +
        "</SrvCreatePurchaseRq>";

    String xsdFile = "src/main/resources/xsd/sb_scheme.xsd";

    JAXBContext jaxbContext;
    try
    {
      jaxbContext = JAXBContext.newInstance(SrvCreatePurchaseRq.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      //Setup schema validator
      SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema itemSchema = sf.newSchema(new File(xsdFile));
      jaxbUnmarshaller.setSchema(itemSchema);

      //Unmarshal xml file
      SrvCreatePurchaseRq request = (SrvCreatePurchaseRq) jaxbUnmarshaller
          .unmarshal(new StringReader(xmlString));

      System.out.println(request + "\n" +
          request.getName() + "\n" +
          request.getLastName() + "\n" +
          request.getAge() + "\n" +
          request.getPurchaseItem() + "\n" +
          request.getCount() + "\n" +
          request.getAmount()+ "\n" +
          request.getPurchaseDate() + "\n"
      );

    }
    catch (JAXBException | SAXException e)
    {
      e.printStackTrace();
      System.out.println("JAXB error" + e.getCause().getMessage());
    }


  }


}
