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
        File file = new File(pathToLauncher);
        deleteFile(file.getAbsolutePath());
        deleteFile(pathToLauncherDir);
        if (file.exists()) {
            while (file.exists()) {
                deleteFile(file.getAbsolutePath());
            }
        }
        Updater.DownloadUpdate(pathToLauncher);


        System.err.println("Запускаю лаунчер..");
        System.err.println("Команда запуска лаунчера: " + "java -jar \"" + pathToLauncher + "\"" + " deleteUpdater");
        Runtime.getRuntime().exec("java -jar \"" + pathToLauncher + "\"" + " deleteUpdater");
        System.err.println("Вы можете закрыть это окно.");
        //Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");

    }

    static void deleteFile(String file1){

        File file = new File(file1);
        System.err.println("Попытка удалить файл: " + file.getAbsolutePath());
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        if (file.delete()){
            System.err.println("Удален файл: " + file.getAbsolutePath());
        }
    }
}
