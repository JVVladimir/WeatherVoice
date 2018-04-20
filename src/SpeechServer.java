import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

/**
 * Created by Владимир on 02.08.2017.
 */
public class SpeechServer {

    private static String URL1 = "https://tts.voicetech.yandex.net/generate?text=";
    private static String URL2 = "&format=wav&lang=ru-RU&speaker=oksana&speed=1.0&emotion=good&key=15e76d91-4f7b-417e-836b-1b57ba6d13a2";

    public BufferedInputStream getSpeech(String text) throws IOException {
        URL url = new URL(URL1 + text + URL2);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        InputStream input = connection.getInputStream();
        BufferedInputStream buffer = new BufferedInputStream(input);
        return buffer;
    }
}
