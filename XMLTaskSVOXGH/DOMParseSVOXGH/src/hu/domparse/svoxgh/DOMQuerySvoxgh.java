package hu.domparse.svoxgh;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQuerySvoxgh {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		File xmlFile = new File("src/hu/domparse/svoxgh/XMLSvoxgh.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = factory.newDocumentBuilder();
		
		Document doc = dBuilder.parse(xmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root: " + doc.getDocumentElement().getNodeName() + "\n");
		
		// Kiirja azokat a markakat, amelyekbol van fekete szinu cipo
		System.out.println("A fekete szinu cipok markai: \n");
		
		NodeList nodeList = doc.getElementsByTagName("cipo");
		
		boolean foundAny = false;
		
		for(int i = 0; i < nodeList.getLength(); i++) {
			
			Node node = nodeList.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				
				Node node2;
				
				// Megvizsgalom a cipo szinet
				node2 = elem.getElementsByTagName("szin").item(0);
				String color = node2.getTextContent();
				
				// Ha fekete, akkor kiiratom a markat
				if("Fekete".equals(color)) {
					Node nodeName = elem.getElementsByTagName("marka").item(0);
					String brand = nodeName.getTextContent();
					
					System.out.println(brand + "\n");
					
					foundAny = true;
				}
			}
			
		}
		
		if(foundAny == false) {
			
			System.out.println("\n Nem talalhato olyan marka, amely arul fekete cipot. \n");
		}
		else {
			foundAny = false;
		}
			
		// Kilistazza azoknak a rendeleseknek az azonositojat, amelyeket a GLS Futarszolgalat kezbesit
		System.out.println("\n GLS altal kezbesitendo rendelesek azonositoi: \n");
		
		nodeList = doc.getElementsByTagName("futarszolgalat");
		
		foundAny = false;
		
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;
				
				Node node2;
				
				// Megvizsgalja a futarszolgalatokat
				node2 = elem.getElementsByTagName("nev").item(0);
				String deliveryCompanyName = node2.getTextContent();
				
				// Ha GLS Futarszolgalat, akkor kiirja a rendeles azonositojat
				if("GLS Futarszolgalat".equals(deliveryCompanyName)) {
					
					String order_id = elem.getAttribute("R_id_fsz");
					
					System.out.println(order_id + "\n");
					
					foundAny = true;
					
				}
			}
		}
		
		if(foundAny == false) {
			System.out.println("\n Nem talalhato olyan rendeles az adatbazisban, amelyet a GLS Futarszolgalat kezbesit.\n");
		}
		
	}

}
