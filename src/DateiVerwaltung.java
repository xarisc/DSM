public class DateiVerwaltung {
          try {
            File inputFile = new File("house+owner.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList owner_List = doc.getElementsByTagName("owner");
            NodeList immobil_List = doc.getElementsByTagName("immo");;
            
            for (int temp = 0; temp < owner_List.getLength(); temp++) {
                Node ownerNode = owner_List.item(temp);
                if (ownerNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) ownerNode;
                    var inhaber_id = eElement.getAttribute("id");
                    var vorname = eElement.getAttribute("firstname");
                    var name = eElement.getAttribute("lastname");
                }
            }

            for (int num = 0; num < immobil_List.getLength(); num++){
                Node immoNode = immobil_List.item(num);
                if (immoNode.getNodeType() == Node.ELEMENT_NODE){
                    Element immoElement = (Element) immoNode;
                    var immobilien_id = immoElement.getElementsByTagName("id").item(0).getTextContent();
                    var art = immoElement.getElementsByTagName("art").item(0).getTextContent();
                    var str = immoElement.getElementsByTagName("str").item(0).getTextContent();
                    var hsnr = immoElement.getElementsByTagName("hsnr").item(0).getTextContent();
                    var plz = immoElement.getElementsByTagName("plz").item(0).getTextContent();
                    var ort = immoElement.getElementsByTagName("ort").item(0).getTextContent();
                    var inhaber = immoElement.getElementsByTagName("owner_id").item(0).getTextContent();
                        }
                    }
          }
}
