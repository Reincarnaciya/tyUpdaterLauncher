package Utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Updater {
    public static boolean downloading = false;

    private static final char[] animationChars = new char[]{'|', '/', '-', '\\'}; // символы загрузки

    //http://typro.space/files/TyLauncher.jar

    public static void DownloadUpdate(String path) {
        try {
            downloading = true;
            System.err.println("Подключаюсь к серверу..");
            URL url = new URL("https://typro.space/files/launcher/TyLauncher.exe");
            HttpsURLConnection updcon = (HttpsURLConnection) url.openConnection();
            System.out.println(updcon);
            long cll_web = updcon.getContentLength();
            File pcFile = new File(path);
            System.err.println("path = " + path);
            System.err.println("pcFile = " + pcFile.getAbsolutePath());
            System.err.println("Размер нового лаунчера: " + cll_web);
            pcFile.createNewFile();
            if ((pcFile.length() != cll_web) && cll_web > 1) {
                System.out.println("Скачиваем новый лаунчер!\n");
                BufferedInputStream bis = new BufferedInputStream(updcon.getInputStream());
                FileOutputStream fw = new FileOutputStream(pcFile);
                byte[] by = new byte[1024];
                int count = 0;
                long progress = 0; // progress as a percentage
                System.out.print("Начало загрузки...");
                while ((count = bis.read(by)) != -1) {
                    fw.write(by, 0, count);
                    long current_progress = (pcFile.length()*100)/cll_web; // расчет процента успеха
                    if (current_progress > progress){ // Если значение изменилось, то запоминаем его и выводим
                        System.err.print("\rprogress: " + current_progress + "% / 100%  ["
                                + animationChars[(int) (current_progress % animationChars.length)] + ']');
                        progress = current_progress;
                    }
                }
                System.out.print("\n\n");
                bis.close();
                fw.close();
            }
        } catch (IOException e) {
            downloading = false;
            e.printStackTrace();
        }finally {
            downloading = false;
        }
    }
}

