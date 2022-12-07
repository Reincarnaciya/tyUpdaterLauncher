import java.io.File;
import java.io.IOException;


public class Main {
    public static String pathToLauncher;
    public static String pathToLauncherDir;

    public static void main(String[] args) throws IOException {
        pathToLauncher = args[0];
        pathToLauncherDir = args[1];
        deleteFile(pathToLauncher);
        deleteFile(pathToLauncherDir);
        if(!new File(pathToLauncher).exists()){
            Utils.Updater.DownloadUpdate(pathToLauncher);
        }
        Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + pathToLauncher + "\""});

    }

    static void deleteFile(String file){
        new File(file).delete();
    }
}