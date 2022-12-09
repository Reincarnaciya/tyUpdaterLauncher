import java.io.File;
import java.io.IOException;


public class Main {
    public static String pathToLauncher;
    public static String pathToLauncherDir;



    public int main(String[] args) throws IOException, InterruptedException {
        pathToLauncher = args[0];
        pathToLauncherDir = args[1];
        deleteFile(pathToLauncher);
        deleteFile(pathToLauncherDir);


        Utils.Updater.DownloadUpdate(pathToLauncher);

        System.err.println("Проверка..");
        Thread.sleep(600);
        System.err.println("Запускаю лаунчер..");
        Runtime.getRuntime().exec("java -jar \"" + pathToLauncher + "\"" + " deleteUpdater");
        System.err.println("Вы можете закрыть это окно.");
        Runtime.getRuntime().exec("taskkill /F /IM cmd.exe");
        Runtime.getRuntime().exit(0);
        Runtime.getRuntime().halt(0);
        System.exit(0);

        if(!new File(pathToLauncher).exists()){
            Utils.Updater.DownloadUpdate(pathToLauncher);
        }
        Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + pathToLauncher + "\""});
        return 0;

    }

    static void deleteFile(String file){
        new File(file).delete();
    }
}
