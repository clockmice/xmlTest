import java.io.File;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Main {

    public static void main(String[] args) {
	// write your code here
        List<Butik> systembolaget = new ArrayList<>();

        try {

            //File fXmlFile = new File("C:/Users/vasil/Desktop/bolaget.xml");

            URL url = new URL("http://www.systembolaget.se/api/assortment/stores/xml");
            URLConnection conn = url.openConnection();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(conn.getInputStream());

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            NodeList nList = doc.getElementsByTagName("ButikOmbud");

            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                //System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    Butik butik = new Butik();
                    butik.setName(eElement.getElementsByTagName("Namn").item(0).getTextContent());
                    butik.setCity(eElement.getElementsByTagName("Address4").item(0).getTextContent());
                    systembolaget.add(butik);
//                    System.out.println("Name : " + eElement.getElementsByTagName("Namn").item(0).getTextContent());
//                    System.out.println("Address : " + eElement.getElementsByTagName("Address1").item(0).getTextContent());
//                    System.out.println("City : " + eElement.getElementsByTagName("Address4").item(0).getTextContent());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Butik butik : systembolaget) {
            System.out.println(butik.getName());
            System.out.println(butik.getCity());
            
        }

    }
}
