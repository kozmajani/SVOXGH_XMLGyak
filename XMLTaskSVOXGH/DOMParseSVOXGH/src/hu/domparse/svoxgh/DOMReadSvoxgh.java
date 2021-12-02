package hu.domparse.svoxgh;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMReadSvoxgh {

	private static void printNode(Node r) {
		
		// Node nevenek kiirása
		if (r.getNodeName() != "#text") {
			System.out.println(r.getNodeName());
		}
		
		// Gyerekek node listaba helyezese
		NodeList children = r.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			boolean isComplex = child.getTextContent().contains("\n");
			
			// Attribute kiirasa
			if (child.hasAttributes()) {
				NamedNodeMap attributes = child.getAttributes();
				int numAttrs = attributes.getLength();
				for (int j = 0; j < numAttrs; j++) {
					Attr attr = (Attr) attributes.item(j);
					String attrName = attr.getNodeName();
					String attrValue = attr.getNodeValue();
					System.out.println(" " + attrName + " : " + attrValue);
				}
			}

			// Nev es tartalom kiirasa
			if (isComplex) {
				printNode(child);
			} else {
				System.out.print(" " + child.getNodeName());
				System.out.println(": " + child.getTextContent());
			}
		}
	}
	
	public static void main(String[] args) {
		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
			Document doc = dbBuilder.parse("src/hu/domparse/svoxgh/XMLSvoxgh.xml");
			String filepath = "src/hu/domparse/svoxgh/XMLSvoxgh.xml";
			doc.getDocumentElement().normalize();

			// Xpath
			XPath xPath = XPathFactory.newInstance().newXPath();

			Element root = doc.getDocumentElement();
			
			System.out.println("\n ----------------\n Modositas elott: \n ---------------- \n");
			
			printNode(root);
			DOMModifySvoxgh.modifyPrices(root);
			
			System.out.println("\n ----------------\n Modositas utan: \n ---------------- \n");
			
			printNode(root);

			// Modositott XML mentese
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath));
			transformer.transform(source, result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}