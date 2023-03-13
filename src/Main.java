/*
 * @author Reincarnaciya
 * Git - https://github.com/Reincarnaciya
 *
 * Assistant patch3
 * Git - https://github.com/patch3
 *
 * @version 0.5
 */

import Utils.Updater;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;


public class Main {
    private static final boolean debug = false; // debug mod
    private static Path[] paths;
    public static String pathToLauncher;
    public static String pathToLauncherDir;

    public static void main(String[] args) {
        try {
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


            if(debug){
                pathToLauncher = "C:/Users/RC/Documents/TyLauncher.exe";
                pathToLauncherDir = "C:\\Users\\RC\\AppData\\Roaming\\.TyPro";
            }else {
                pathToLauncher = args[0];
                pathToLauncherDir = args[1];
            }
            paths = new Path[]{
                    Paths.get(pathToLauncherDir + "\\clients\\"),
                    Paths.get(pathToLauncherDir+"\\runtime\\"),
                    Paths.get(pathToLauncherDir+"\\auth.json"),
                    Paths.get(pathToLauncherDir+"\\settings.json")
            };

            System.err.println(Arrays.toString(args));
            System.err.println("Удаляю старые файлы..");
            File file = new File(pathToLauncher);
            deleteFile(new File(pathToLauncherDir));

            System.err.println( new File(pathToLauncherDir + File.separator + "clients").getAbsolutePath());

            if (file.exists()) {
                file.delete();
            }
            System.err.println("Начинаю закачку нового лаунчера..");
            Updater.DownloadUpdate(pathToLauncher);

            System.err.println("pathtoLauncher = " + pathToLauncher);

            //Desktop.getDesktop().open(new File(pathToLauncher));


            Runtime.getRuntime().exec(pathToLauncher);


            System.err.println("Лаунчер успешно обновлен! Откройте его :)");

            System.err.println("Вы можете закрыть это окно.");
            System.exit(0);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteFile(File dir) {
        for (File file : dir.listFiles()) {
            if (Arrays.stream(paths).anyMatch(path -> path.startsWith(file.getAbsolutePath()))) continue;
            if (file.isDirectory()) {
                deleteFile(file);
            } else {
                file.delete();
            }
        }
    }
}
