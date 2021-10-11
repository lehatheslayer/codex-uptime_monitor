package so.codex.monitor.controllers;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {
    private final static String PATH = "Addresses.xml";
    private final static String TAG = "url";

    private static List<String> parse(Document document) {
        final NodeList tmpAddresses = document.getElementsByTagName(TAG);
        final List<String> addresses = new ArrayList<>();

        for (int i = 0; i < tmpAddresses.getLength(); i++) {
            addresses.add(tmpAddresses.item(i).getTextContent());
        }

        return addresses;
    }

    public static List<String> getAddresses() throws ParserConfigurationException, IOException, SAXException {
        final Document document = DocumentBuilderFactory
                .newInstance()
                .newDocumentBuilder()
                .parse(PATH);

        return parse(document);
    }
}
