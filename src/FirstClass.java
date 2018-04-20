import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Scanner;

/**
 * Created by Владимир on 18.06.2017.
 */
public class FirstClass {

    private static Scanner scan;
    private SpeechRecorder speechRecorder;

    FirstClass() throws IOException {
        System.out.println("***Начало работы***");
        speechRecorder = new SpeechRecorder();
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        while (!s.equals("stop")) {
            if (s.equals("r")) {
                int a = speechRecorder.startRecord();
                if (a == -1) {
                    speechRecorder = new SpeechRecorder();
                    speechRecorder.startRecord();
                }

            }
            if (s.equals("s"))
                speechRecorder.stopRecord();
            s = null;
            s = scan.nextLine();
        }
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        /*String text = "A  frgrg   man a plan";
        String pattern = "man";
        StringAlgorithms str = new StringAlgorithms();
        int a = str.searchSubstring(text, pattern);
        System.out.println(a);
        int b = str.searchBoyerMoore(text, pattern);
        System.out.println(b);*/
        //  new FirstClass();
        /*SpeechRecorder speechRecorder = new SpeechRecorder();
        Scanner scan = new Scanner(System.in);
        speechRecorder.startRecord();
        boolean b = scan.nextBoolean();
        if (b)
            speechRecorder.stopRecord();*/
        WeatherServer w = new WeatherServer();
        w.getWeather();
        String text = w.getJSONInfo();
        SpeechServer s = new SpeechServer();
        BufferedInputStream voice = s.getSpeech(URLEncoder.encode(text, "UTF-8"));
        new MediaPlayer(voice);
        /*scan = new Scanner(System.in);
        String text = getString();
        while (!text.equals("Выход")) {
            SpeechServer s = new SpeechServer();
            new MediaPlayer(s.getSpeech(URLEncoder.encode(text, "UTF-8")));
            text = getString();
        }*/
    }

    public static String getString() {
        return scan.nextLine();
    }
}
