package so.codex.monitor.service.monitor;

import java.io.IOException;

/**
 * Класс, который просит codex бота оповестить о проблеме в телеграм
 */
public class Notifier {
    private static final String WEB_HOOK = "https://notify.bot.codex.so/u/60LVD6GR";

    public static void sendAlert(String message) {
        final Runtime rt = Runtime.getRuntime();

        try {
            rt.exec("curl -X POST " + WEB_HOOK + " -d \"message=" + message + "\" -k");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
