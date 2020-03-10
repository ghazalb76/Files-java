import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Manager {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Please enter the path of directory which contains your .txt files (example: /home/qhazale/lessons/java/files/)");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        System.out.println("Please enter the word you want to find in files:");
        scanner = new Scanner(System.in);
        String[] searchWord = scanner.nextLine().split(" ");

        FileReader reader = new FileReader();
        File[] files = reader.readFiles(path);

        Searcher search = new Searcher();
        for(File file:files){
            search.findWords(file, searchWord);
        }

    }
}
