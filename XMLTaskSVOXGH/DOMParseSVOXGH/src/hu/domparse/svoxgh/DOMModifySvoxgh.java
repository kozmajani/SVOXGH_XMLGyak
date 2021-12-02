package hu.domparse.svoxgh;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DOMModifySvoxgh {

	public static void modifyPrices(Node root) {
		try {
			
			System.out.println(root.getNodeType());
			NodeList els = root.getChildNodes();
			
// Vegigmegy a root (cipo_rendeles) gyerekein
			for (int j = 0; j < els.getLength(); j++) {
				
// Ha a gyerek neve cipo, akkor vegrehajtja a kovetkezoket
				if (els.item(j).getNodeName() == "cipo") {
						
// Vegigmegy a gyerek elemein
					for (int k = 0; k < els.item(j).getChildNodes().getLength(); k++) {
								
// Ha az egyik elem neve ar, akkor megnoveli annak erteket 3000-rel
						if (els.item(j).getChildNodes().item(k).getNodeName() == "ar") {	
									
							int ar = Integer.parseInt(els.item(j).getChildNodes().item(k).getTextContent());
							els.item(j).getChildNodes().item(k).setTextContent(String.valueOf(ar + 3000));
									
						}		
					}	
				}
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}