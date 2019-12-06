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
import ru.smartsoft.dto.PurchaseRequestTo;
import ru.smartsoft.model.Item;

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

    String xmlString = "<purchaseRequest>" +
        "    <itemName>Tv</itemName>" +
        "    <count>1</count>" +
        "</purchaseRequest>";

    String xsdFile = "src/main/resources/xsd/purchase_request.xsd";

    JAXBContext jaxbContext;
    try
    {
      jaxbContext = JAXBContext.newInstance(PurchaseRequestTo.class);
      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

      //Setup schema validator
      SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
      Schema itemSchema = sf.newSchema(new File(xsdFile));
      jaxbUnmarshaller.setSchema(itemSchema);

      //Unmarshal xml file
      PurchaseRequestTo item = (PurchaseRequestTo) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));

      System.out.println(item);
    }
    catch (JAXBException | SAXException e)
    {
      e.printStackTrace();
    }


  }


}
