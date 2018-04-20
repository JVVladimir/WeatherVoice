import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;

/**
 * Created by Владимир on 02.08.2017.
 */
public class WeatherServer {

    private static final String KEY = "http://api.openweathermap.org/data/2.5/forecast?id=524901&APPID=e125f275982a0789489095428b3afb35";
    // Текст с погодой от сервера
    private StringBuilder text;
    // Текст для озвучивания
    private StringBuilder info;
    // Описание погоды: Добавить для зимы, грозы и прочего
    private String[] description = {"scattered clouds", "broken clouds", "few clouds", "overcast clouds", "moderate rain", "light rain", "clear sky"};
    private String[] descriptionRUS = {"незначительная облачность", "облачно с прояснениями", "небольшая облачность", "пасмурно", "умеренный дождь", "лёгкий дождик", "чистое небо"};

    WeatherServer() {
        text = new StringBuilder();
        info = new StringBuilder();
    }

    public void getWeather() throws IOException {
        URL url = new URL(KEY);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        InputStream input = connection.getInputStream();
        int c;
        BufferedInputStream buffer = new BufferedInputStream(input);
        while ((c = buffer.read()) != -1)
            text.append((char) c);
        input.close();
        buffer.close();
         //System.out.println(text.toString());
        extractInfoFromJSON();
    }

    private void extractInfoFromJSON() {
        StringAlgorithms alg = new StringAlgorithms();
        int a1 = alg.searchSubstring(text.toString(), "temp");
        int a2 = alg.searchSubstring(text.toString(), "pressure");
        int a3 = alg.searchSubstring(text.toString(), "humidity");
        int a4 = alg.searchSubstring(text.toString(), "speed");
        int a5 = alg.searchSubstring(text.toString(), "description");
        info.append("По данным на " + Calendar.getInstance().getTime().getHours() + " ч \n");
        info.append("На улице " + translater(a5) + "\n");
        info.append("Температура равна " + (int) (Double.valueOf(text.substring(a1 + 6, a1 + 11)) - 273.15) + " °C\n");
        info.append("Давление составляет " + (int) (Double.valueOf(text.substring(a2 + 10, a2 + 17)) * 0.75) + " мм рт.ст.\n");
        info.append("Влажность воздуха " + text.substring(a3 + 10, a3 + 12) + " %\n");
        info.append("Скорость ветра " + (int) (Double.valueOf(text.substring(a4 + 7, a4 + 11)) / 3600 * 1000) + " м/с");
    }

    private String translater(int a5) {
        String s = text.substring(a5 + 14, a5 + 40);
        int d = -1;
        for (int i = 0; i < description.length; i++)
            if (s.contains(description[i])) {
                d = i;
                break;
            }
        if(d == -1)
            return "непонятно что";
        return new String(descriptionRUS[d]);
    }

    public String getJSONInfo() {
        return info.toString();
    }

}
