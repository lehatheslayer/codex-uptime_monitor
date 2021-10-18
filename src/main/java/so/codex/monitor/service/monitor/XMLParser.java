package so.codex.monitor.service.monitor;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * XML парсер. Парсит адреса сайтов в поле URLS
 */
public class XMLParser {
    /**
     * Addresses.xml - XML файл, хранящий адреса сайтов
     */
    private final static String PATH = "Addresses.xml";

    /**
     * Тэг, в котором хранятся адреса сайтов
     */
    private final static String TAG = "url";

    /**
     * Список, куда будут сохранены все адреса
     */
    private final static List<String> URLS = new ArrayList<>();

    /**
     * Метод считывает адреса сайтов из PATH и тэга TAG в URLS
     */
    private static void parse() {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(PATH);
            final NodeList tmpAddresses = document.getElementsByTagName(TAG);

            for (int i = 0; i < tmpAddresses.getLength(); i++) {
                URLS.add(tmpAddresses.item(i).getTextContent());
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает все адреса сайтов.
     * Если список URLS пуст, то вызывает метод parse()
     *
     * @return список адресов сайтов
     */
    public static List<String> getUrls() {
        if (URLS.size() == 0) {
            parse();
        }

        return URLS;
    }
}
