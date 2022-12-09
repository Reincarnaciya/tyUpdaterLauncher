import Utils.Updater;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class Main {
    public static String pathToLauncher;
    public static String pathToLauncherDir;



    public static void main(String[] args) throws IOException {
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


        System.err.println("Запускаю лаунчер..");
        Runtime.getRuntime().exec("java -jar \"" + pathToLauncher + "\"" + " deleteUpdater");
        System.err.println("Вы можете закрыть это окно.");
        //Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");

    }

    static void deleteFile(String file1){

        File file = new File(file1);
        System.err.println("Передан файл: " + file.getAbsolutePath());
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        System.err.println("Удален файл: " + file.getAbsolutePath());
        file.delete();
    }
}
