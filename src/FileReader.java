import java.io.File;

public class FileReader {
    public File[] readFiles(String path) {
        File file = new File(path);
        File[] fileArray = file.listFiles();
        return fileArray;
    }
}
