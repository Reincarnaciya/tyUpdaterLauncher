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

    //http://typro.space/files/launcher/TyLauncher.jar

    public static void DownloadUpdate(String path) {
        try {
            downloading = true;
            URL url = new URL("https://typro.space/files/launcher/tyProLauncher.jar");
            HttpsURLConnection updcon = (HttpsURLConnection) url.openConnection();
            System.out.println(updcon);
            long cll_web = updcon.getContentLength();
            File pcFile = new File(path);
            System.err.println("path = " + path);
            System.err.println("pcFile = " + pcFile.getAbsolutePath());
            System.err.println(cll_web);
            pcFile.createNewFile();
            System.err.println(path + File.separator + "TyLauncher.jar");
            if ((pcFile.length() != cll_web) && cll_web > 1) {
                BufferedInputStream bis = new BufferedInputStream(updcon.getInputStream());
                FileOutputStream fw = new FileOutputStream(pcFile);
                byte[] by = new byte[1024];
                int count = 0;
                System.out.println("Скачиваем новый лаунчер..");
                while ((count = bis.read(by)) != -1) {
                    fw.write(by, 0, count);
                }
                fw.close();

            }
            downloading = true;

        } catch (IOException e) {
            downloading = false;
            e.printStackTrace();
        }

    }
}

