import java.util.*;
/**
 * Please read the Readme.txt file for all the requirements and information about this
 * exercise.
 * 
 * @author (Abraham Ferreo) 
 * @version (31/OCT/2017)
 */
public class DNA {
    private HashMap<String,Integer> dnaHashMap;
    
    public DNA(){
        dnaHashMap = new HashMap<String, Integer>();
    }
    
    public void buildCodonMap(int start, String dna){
        dnaHashMap.clear();
        int count = 0;
        /*We start a for loop from the value in the start variable, until dna.lentgth()-2
         * because it might read 2 extra digits in the string as we count the digits 3 by 3
         */
        for (int i=start; i<dna.length()-2; i+=3){
            //This is our codon, from start to 2 more characters, as String
            String codon = dna.substring(i, i+3);
            /*If during our loop, the codon found is a new one, we add it to our hashmap
             * as a key and with value 1. As we want to count how many different codons we
             * found in our print statement, we will add 1 to count every time we find one.
             */
            if (!dnaHashMap.containsKey(codon)){
                dnaHashMap.put(codon, 1);
                count ++;
                // For test purposes: System.out.println("adding new word to hashmap!");
            }
            //If the codon is already a Key, we add one to the value of that codon.
            else{
                dnaHashMap.put(codon,dnaHashMap.get(codon) + 1);
                //For test purposes: System.out.println("adding 1 to existing codon!");
            }
        }
        
        System.out.println("Reading frame starting with " + start +
                           ", results in " + count + " unique codons");
    }
    
    public String getMostCommonCodon(){
        int maxValue = 0;
        String bestValue = "";
        for (String s : dnaHashMap.keySet()){
            if (dnaHashMap.get(s) > maxValue){
                maxValue = dnaHashMap.get(s); 
                bestValue = s;
            }
        }
        return "and most common codon is " + bestValue.toUpperCase() + " with count " + maxValue;
    }
    
    public void printCodonCounts(int start, int end){
        System.out.println("Counts of codons between " + start +  " and " + end + " inclusive are: \n");
        for (String s : dnaHashMap.keySet()){
            int HashMap = dnaHashMap.get(s);
            if (HashMap >= start && HashMap <= end){
                System.out.println( s.toUpperCase() + "\t" + dnaHashMap.get(s));
            }
        }
    }
    //ahora lo tuneamos todo y este assignment esta hecho
    public void test(){
        int start = 0;
        String dna = "CAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCTAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATCCAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCAGCAGCCCAGAACCAACTGCATAACATACAACCTTTAAAAGGAAGAAATCGCACCAGCCCAGAATCAACTGCATAACATACAAACTTTAAAAGGAAGAAATC";
        dna = dna.toLowerCase();
        buildCodonMap(start,dna);
        System.out.println(getMostCommonCodon());
        printCodonCounts(5,8);
    }
}
