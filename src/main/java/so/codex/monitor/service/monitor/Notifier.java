package so.codex.monitor.service.monitor;

import java.io.IOException;

/**
 * Класс, который просит codex бота оповестить о проблеме в телеграм
 */
public class Notifier {
    public static void sendAlert(String message) {
        final Runtime rt = Runtime.getRuntime();

        try {
            rt.exec("curl -X POST " + XMLParser.getWebHook() + " -d \"message=" + message + "\" -k");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
