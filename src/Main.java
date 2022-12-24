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
        deleteFile(new File(pathToLauncherDir), new File(pathToLauncherDir + File.separator + "clients"));
        System.err.println( new File(pathToLauncherDir + File.separator + "clients").getAbsolutePath());
        if (file.exists()) {
           while (file.exists()) {
               file.delete();
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
    public static void deleteFile(File dir, File excludedDir) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                if (!file.equals(excludedDir)) {
                    deleteFile(file, excludedDir);
                    file.delete();
                }
            } else {
                file.delete();
            }
        }
    }
}
