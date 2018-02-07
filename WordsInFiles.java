import edu.duke.*;
import java.io.File;
import java.util.*;
/**
 * This class belongs to the Assignment 2 exercise. For mor info, 
 * please read the README.TXT file. 
 * 
 * @author (Abraham Ferrero) 
 * @version (02/NOV/2017)
 */
public class WordsInFiles {
    private HashMap<String, ArrayList> hashWords;
    
    public WordsInFiles(){
        hashWords = new HashMap<String, ArrayList>();
    }
    
    //This method takes a file and process the every word in them:
    private void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for (String word : fr.words()){
           /*If the word is not in the Hashmap, it gets added as a Key, and the filename
            * gets stored as a value. 
            */
            if (!hashWords.containsKey(word)){
               ArrayList<String> sourceF = new ArrayList<String>();
               sourceF.add(f.getName());
               hashWords.put(word, sourceF);
            }
           else {
               /*If the filename is not already there, it gets stored as a new value in the
                * arraylist of the hashmap:
                */
               ArrayList<String> sourceF = new ArrayList<String>();
               sourceF = hashWords.get(word);
               if ( !sourceF.contains(f.getName())) {
                        sourceF.add(f.getName());
                  }
           }
        }
    }
    
    public void buildWordFileMap(){
       //This method calls the method above, which was private, for every file selected.
       hashWords.clear();
       DirectoryResource dr = new DirectoryResource();
       for (File f : dr.selectedFiles()){ 
           addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        //This method iterates every value of the hashmap as a number, giving you the max value. 
        int maxSize = 0;
        for (ArrayList s : hashWords.values()){
            if (s.size() > maxSize) {
                maxSize = s.size();
            }
        }
        return maxSize;
    }
    
    public ArrayList wordsInNumFiles(int number){
        /*This method creates a new arrayList with the words repeated the same amount of times
         * that the number we give as a parameter.
         */
        System.out.println("\nThis words appear " + number + " times: ");
        ArrayList<String> words = new ArrayList<String>();
        int counting =0;
        for (String word : hashWords.keySet()){
            int counts = hashWords.get(word).size();
            if(counts == number){
                words.add(word);
                counting++;
            }
        }
        System.out.println("total of words repeated " + number + " times: " + counting);
        return words;
    }
    
    public void printFilesIn(String word){
        /*This method takes the word given as a parameter, and prints every filename of every
         * file where it appears.
         */
        System.out.println("\nThe word " + word + " is in the following files: ");
        for (String s : hashWords.keySet()){
            if (s.equals(word)){
                ArrayList wordAndFiles = hashWords.get(s);
                for (int i=0; i< wordAndFiles.size(); i++){
                    System.out.println(wordAndFiles.get(i));
                }
            }
        }
    }
    
    public void tester(){
        buildWordFileMap();
        ArrayList wordsInNumFiles = wordsInNumFiles(4);
        for (int i=0; i < wordsInNumFiles.size(); i++){
           System.out.println(wordsInNumFiles.get(i));
        }
        System.out.println("\nMaximum number of words in all the files given = " +maxNumber());
        printFilesIn("tree");
        System.out.println("\n");
        for (String s :hashWords.keySet() ){
            System.out.println(s + hashWords.get(s) );
        }
       
    }
}
