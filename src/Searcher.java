

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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


    public void printFilesName(File file, String[] searchWord, boolean[] wordExists) throws FileNotFoundException {
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
