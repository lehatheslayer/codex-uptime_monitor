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
 * XML парсер. Парсит адреса сайтов в поле urls.
 * Парсит вебхук телеграм бота CodeX Bot в поле webHook
 */
public class XMLParser {
    /**
     * Addresses.xml - XML файл, хранящий адреса сайтов
     */
    private final static String PATH = "Addresses.xml";

    /**
     * Тэг, в котором хранятся адреса сайтов
     */
    private final static String ADDRESSES_TAG = "url";

    /**
     * Список, куда будут сохранены все адреса
     */
    private static final List<String> urls = new ArrayList<>();

    /**
     * Метод считывает адреса сайтов из PATH и тэга TAG в URLS
     */
    private static void parse() {
        try {
            final Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(PATH);
            final NodeList addressesNodeList = document.getElementsByTagName(ADDRESSES_TAG);

            for (int i = 0; i < addressesNodeList.getLength(); i++) {
                urls.add(addressesNodeList.item(i).getTextContent());
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
        if (urls.size() == 0) {
            parse();
        }

        return urls;
    }
}
