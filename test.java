// Java Imports
import java.util.*;
import java.io.File; 
import java.io.FileNotFoundException;


public class Manager {
    public static void main(String[] args) throws FileNotFoundException{
        
        System.out.println("Please enter the path of directory which contains your .txt files (example: /home/qhazale/lessons/java/files/)");
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        System.out.println("Please enter the word you want to find in files:");
        scanner = new Scanner(System.in);
        String[] searchWord = scanner.nextLine().split(" ");

        // String path = "/home/qhazale/lessons/java/files/";
        FileReader reader = new FileReader();
        File[] files = reader.readFiles(path);

        Searcher search = new Searcher();
        if(searchWord.length > 3){
            for(File f:files){
                search.searchTypeFour(f, searchWord);
            }
        }
        else if(searchWord.length == 1){
            for(File f:files){
                search.searchTypeOne(f, searchWord[0]);
            }
        }
        else if(searchWord[1].equals("AND")){
            for(File f:files){
                search.searchTypeTwo(f, searchWord[0], searchWord[2]);
            }
        }
        else if(searchWord[1].equals("OR")){
            for(File f:files){
                search.searchTypeThree(f, searchWord[0], searchWord[2]);
            }

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

    public void searchTypeOne(File f, String searchWord) throws FileNotFoundException{
        Scanner s = new Scanner(f);
        while(s.hasNextLine()){
            String nextLine = s.nextLine();
            if(nextLine.contains(searchWord)){
                System.out.println(f);
            }
        }
    }


    public void searchTypeTwo(File f, String searchWord1,String searchWord2) throws FileNotFoundException{
        Scanner s = new Scanner(f);
        boolean word1Existance = false, word2Existance = false;
        while(s.hasNextLine()){
            String nextLine = s.nextLine();
            if(nextLine.contains(searchWord1)){
                word1Existance = true;
            }
            if(nextLine.contains(searchWord2)){
                word2Existance = true;
            }
        }
        if(word1Existance && word2Existance){
            System.out.println(f);
        }
    }


    public void searchTypeThree(File f, String searchWord1,String searchWord2) throws FileNotFoundException{
        Scanner s = new Scanner(f);
        boolean word1Existance = false, word2Existance = false;
        while(s.hasNextLine()){
            String nextLine = s.nextLine();
            if(nextLine.contains(searchWord1) || nextLine.contains(searchWord2)){
                System.out.println(f);
                break;
            }
        }
    }


    public void searchTypeFour(File f, String[] searchWord) throws FileNotFoundException{
        Scanner s = new Scanner(f);
        boolean check[] = new boolean[searchWord.length];
        for(int i =0;i<check.length;i++){
            check[i]= false;
        }
        while(s.hasNextLine()){
            String nextLine = s.nextLine();
            for(int i= 0;i < searchWord.length ;i+=2){
                
                    if(nextLine.contains(searchWord[i])){
                        check[i] = true;
                    }
                }
            
        }
        if(searchWord.length == 1 && check[0]){
            System.out.println(f);
        }
        for(int i =0;i<searchWord.length;i++){
            if(searchWord[i].equals("AND")){
                if(check[i-1] && check[i+1]){
                    System.out.println(f);
                    break;

                }
            }
            else if(searchWord[i].equals("OR")){
                if(i!=searchWord.length-2){
                    if(searchWord[i+2].equals("OR")){
                        if(check[i+1]){
                            System.out.println(f);
                            break;
                        }
                    }
                }
                else if(check[i+1]){
                    System.out.println(f);
                    break;
                }
            }
            else if(i==0 && searchWord[i+1].equals("OR")){
                if(check[i]){
                    System.out.println(f);
                    break;
                }
            }
        }  
    }
}