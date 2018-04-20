import javax.sound.sampled.*;
import java.io.*;

public class SpeechRecorder implements Runnable {

    private AudioFormat format;
    private boolean stopped;
    private TargetDataLine line = null;
    private ByteArrayOutputStream out = null;
    private Thread t;
    private DataLine.Info info;


    SpeechRecorder() throws IOException {
        // Формат записи аудио
        format = new AudioFormat(16000, 16, 1, true, true); // signed
    }

    public int startRecord() {
        stopped = false;
        info = new DataLine.Info(TargetDataLine.class,
                format);
        // Проверяем доступна ли линия для получения аудио потока
        if (!AudioSystem.isLineSupported(info)) {
            System.out.println("Линия не поддерживается!");
        }
        // Открываем линию
        try {
            line = (TargetDataLine) AudioSystem.getLine(info);
            line.open(format);
            // Запуск потока для прослушивания микрофона
            t = new Thread(this);
            t.start();
        } catch (LineUnavailableException ex) {
            System.out.println("Ошибка в открытии линии!");
            line.stop();
            line.drain();
            line.close();
            return -1;
        }
        return 1;
    }

    // Установка флага в true
    public void stopRecord() {
        stopped = true;
    }

    @Override
    public void run() {
        // Создание выходного потока для записи аудио данных
        out = new ByteArrayOutputStream();
        int numBytesRead;
        byte[] data = new byte[(int) format.getSampleRate() *
                format.getFrameSize()];
        // Начало захвата аудио
        line.start();
        // Цикл для чтения данных с микрофона
        while (!stopped) {
            // Чтение данных с TargetDataLine
            numBytesRead = line.read(data, 0, data.length);
            // Сохранение данных в выходной поток
            out.write(data, 0, numBytesRead);
        }
        // Преобразование "сырых" данных в нормальный аудио формат **************************************
        byte audio[] = out.toByteArray();
        InputStream input = new ByteArrayInputStream(audio);
        // Запись звука в wav файл
        createFile(input,audio);
        try {
            out.close();
            line.stop();
            line.drain();
            line.close();
        } catch (IOException e) {
            System.out.println("Все потоки не закрылись");
        }
    }

    // Запись звука в wav файл
    public void createFile(InputStream input, byte[] audio) {
        try {
            AudioInputStream ais = new AudioInputStream(input,
                    format, audio.length / format.getFrameSize());
            File f = new File("D://voice.wav");
            f.createNewFile();
            AudioSystem.write(ais, AudioFileFormat.Type.WAVE, f);
            ais.reset();
            new MediaPlayer(ais);
            ais.close();
        }catch (Exception ex) {
            System.out.println("Ошибка в создании аудио файла");
        }
    }
}