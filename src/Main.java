import Utils.Updater;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class Main {
    public static String pathToLauncher;
    public static String pathToLauncherDir;



    public static void main(String[] args) throws IOException, InterruptedException {
        pathToLauncher = args[0];
        pathToLauncherDir = args[1];
        System.err.println(Arrays.toString(args));
        deleteFile(pathToLauncherDir);
        deleteFile(pathToLauncher);
        while (new File(pathToLauncher).exists()){
            deleteFile(pathToLauncher);
        }
        if(!new File(pathToLauncher).exists()){
            Utils.Updater.DownloadUpdate(pathToLauncher);
        }else System.err.println("Файл есть, пиздец");




        System.err.println("Проверка..");
        //Thread.sleep(600);
        System.err.println("Запускаю лаунчер..");
        Runtime.getRuntime().exec("java -jar \"" + pathToLauncher + "\"" + " deleteUpdater");
        System.err.println("Вы можете закрыть это окно.");
        //Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");

    }

    static void deleteFile(String file){
        new File(file).delete();
    }
}
