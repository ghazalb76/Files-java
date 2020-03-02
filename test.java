// Java Imports
import java.util.*;
import java.io.File; 
import java.io.FileNotFoundException;


public class Manager {
    public static void main(String[] args) throws FileNotFoundException{

        // String path = "/home/qhazale/lessons/java/files/";
        
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

public class FileReader {

    public File[] readFiles(String path) throws FileNotFoundException{
        File file = new File(path);
        File[] fileArray = file.listFiles();
        return fileArray;
    }
}


public class Searcher {

    public void findWords(File file, String[] searchWord) throws FileNotFoundException{
        Scanner scan = new Scanner(file);
        boolean wordExists[] = new boolean[searchWord.length];
        for(int i =0;i<wordExists.length;i++){
            wordExists[i]= false;
        }

        while(scan.hasNextLine()){
            String nextLine = scan.nextLine();
            for(int i= 0;i < searchWord.length ;i+=2){
                if(nextLine.contains(searchWord[i])){
                    wordExists[i] = true;
                }
            }
        }
        printFilesName(file, searchWord, wordExists);  
    }


    public void printFilesName(File file, String[] searchWord, boolean[] wordExists) throws FileNotFoundException{
        if(searchWord.length == 1 && wordExists[0]){
            System.out.println(file);
        }
        for(int i =0;i<searchWord.length;i++){
            if(searchWord[i].equals("AND")){
                if(wordExists[i-1] && wordExists[i+1]){
                    System.out.println(file);
                    break;
                }
            }
            else if(searchWord[i].equals("OR")){
                if(i!=searchWord.length-2){
                    if(searchWord[i+2].equals("OR")){
                        if(wordExists[i+1]){
                            System.out.println(file);
                            break;
                        }
                    }
                }
                else if(wordExists[i+1]){
                    System.out.println(file);
                    break;

                }
            }
            else if(i==0 && searchWord[i+1].equals("OR")){
                if(wordExists[i]){
                    System.out.println(file);
                    break;
                }
            }
        }
    }
}
