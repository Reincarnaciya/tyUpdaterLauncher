import Utils.Updater;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;


public class Main {
    public static String pathToLauncher;
    public static String pathToLauncherDir;

    private static String lastFile = "";



    public static void main(String[] args) throws IOException {
        System.err.println("            _______ __     __ _____  _____  _____            _      \n" +
                "           |__   __|\\ \\   / /|  __ \\|_   _|/ ____|    /\\    | |     \n" +
                "              | |    \\ \\_/ / | |__) | | | | |        /  \\   | |     \n" +
                "              | |     \\   /  |  ___/  | | | |       / /\\ \\  | |     \n" +
                "              | |      | |   | |     _| |_| |____  / ____ \\ | |____ \n" +
                "              |_|      |_|   |_|    |_____|\\_____|/_/    \\_\\|______|\n" +
                "                                                                    \n" +
                "                                                                    \n" +
                "             _    _  _____   _____         _______  ______  _____  \n" +
                "            | |  | ||  __ \\ |  __ \\    /\\ |__   __||  ____||  __ \\ \n" +
                "            | |  | || |__) || |  | |  /  \\   | |   | |__   | |__) |\n" +
                "            | |  | ||  ___/ | |  | | / /\\ \\  | |   |  __|  |  _  / \n" +
                "            | |__| || |     | |__| |/ ____ \\ | |   | |____ | | \\ \\ \n" +
                "             \\____/ |_|     |_____//_/    \\_\\|_|   |______||_|  \\_\\\n" +
                "                                                                   \n" +
                "                                                                   \n" +
                "");

        pathToLauncher = args[0];
        pathToLauncherDir = args[1];
        System.err.println(Arrays.toString(args));
        System.err.println("Удаляю старые файлы..");
        File file = new File(pathToLauncher);
        deleteFile(file.getAbsolutePath());
        deleteFile(pathToLauncherDir);
        if (file.exists()) {
            while (file.exists()) {
                deleteFile(file.getAbsolutePath());
            }
        }
        System.err.println("Начинаю закачку нового лаунчера..");
        Updater.DownloadUpdate(pathToLauncher);


        System.err.println("Запускаю лаунчер..");
        System.err.println("Команда запуска лаунчера: " + "java -jar \"" + pathToLauncher + "\"" + " deleteUpdater");
        Runtime.getRuntime().exec("java -jar \"" + pathToLauncher + "\"" + " deleteUpdater");
        System.err.println("Вы можете закрыть это окно.");
        //Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");

    }

    static void deleteFile(String file1){

        File file = new File(file1);
        if(!lastFile.equals(file1)) System.err.println("Попытка удалить файл: " + file.getAbsolutePath().trim());

        lastFile = file1;
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        if (file.delete()){
            System.err.println("Удален файл: " + file.getAbsolutePath().trim());
        }
    }
}
