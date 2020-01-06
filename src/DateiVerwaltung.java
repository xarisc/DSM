//package com.company;

import java.io.File;
import java.util.Arrays;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class useXML {

    File inputFile = new File("C:\\Users\\computer\\Desktop\\house+owner.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(inputFile);
   // doc.getDocumentElement().normalize();

    static String[] addElement(String[] a, String  e) {
        a  = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
    }
    public String[] getOwner(){
        //list of owner (Inhaber)
        String[] inhaber = new String[0];
        NodeList owner_List = doc.getElementsByTagName("owner");
        for (int temp = 0; temp < owner_List.getLength(); temp++) {
            Node ownerNode = owner_List.item(temp);
            if (ownerNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) ownerNode;
                String id = eElement.getAttribute("id");
                String vorname = eElement.getAttribute("firstname");
                String name = eElement.getAttribute("lastname");
                String newString = id + "." + vorname + "." + name;
                inhaber = addElement(inhaber,newString);
            }
        }
        return inhaber;
    }

    public String[] getImmobilie(){
        //list iof immoblien
        String [] immobilie = new String[];
        NodeList immobil_List = doc.getElementsByTagName("immo");
        for(int num = 0; num<immobil_List.getLength();num++){
            Node immoNode = immobil_List.item(num);
            if (immoNode.getNodeType() == Node.ELEMENT_NODE) {
                Element immoElement = (Element) immoNode;
                String id = immoElement.getElementsByTagName("id").item(0).getTextContent();
                String art = immoElement.getElementsByTagName("art").item(0).getTextContent();
                //String str = immoElement.getElementsByTagName("str").item(0).getTextContent();
                //String hsnr = immoElement.getElementsByTagName("hsnr").item(0).getTextContent();
                //String plz = immoElement.getElementsByTagName("plz").item(0).getTextContent();
                //String ort = immoElement.getElementsByTagName("ort").item(0).getTextContent();
                String inhaber_id = immoElement.getElementsByTagName("owner_id").item(0).getTextContent();
                String newString = id + "" + art + "" + inhaber_id;
                immobilie = addElement(immobilie, newString);
            }
        }
        return immobilie;
    }
}
