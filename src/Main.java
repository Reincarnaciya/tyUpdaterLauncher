/**
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
import java.io.IOException;
import java.util.Arrays;

public class Main {
    private static final boolean debug = true; // debug mod

    private static final String[] exclusions = new String[]{
            "clients",
            "auth.json",
            "settings.json"
    };

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
                pathToLauncher = "C:\\Users\\admin\\Documents\\TyLauncher.exe";
                pathToLauncherDir = "C:\\Users\\admin\\AppData\\Roaming\\.TyPro";
            }else {
                pathToLauncher = args[0];
                pathToLauncherDir = args[1];
            }

            System.err.println(Arrays.toString(args));
            System.err.println("Удаляю старые файлы..");
            File file = new File(pathToLauncher);
            //deleteFile(new File(pathToLauncherDir), new File(pathToLauncherDir + File.separator + "clients"));


            deleteFilesExcept(new File(pathToLauncherDir));

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
//    public static void deleteFile(File dir, File excludedDir) {
//        for (File file : dir.listFiles()) {
//            if (file.isDirectory()) {
//                if (!file.equals(excludedDir)) {
//                    deleteFile(file, excludedDir);
//                    file.delete();
//                }
//            } else {
//                file.delete();
//            }
//        }
//    }

    /**
     * Удаление всех файлов из директории кроме тех что указаны в Main.exclusions
     * @param folder проверяемая папка с удалением
     * */
    public static void deleteFilesExcept(File folder) {
        File[] files = folder.listFiles();

        Arrays.stream(files)
                .filter(file -> !Arrays.stream(Main.exclusions).anyMatch(exclusion -> exclusion.equals(file.getName())))
                .forEach(file -> {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                });
    }
    /**
     * Простое удаление папки
     * @param directory папка, которую надо удалить
     * */
    private static void deleteDirectory(File directory) {
        File[] files = directory.listFiles();

        Arrays.stream(files)
                .forEach(file -> {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                });

        directory.delete();
    }

}



