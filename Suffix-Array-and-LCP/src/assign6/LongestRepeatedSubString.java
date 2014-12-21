package assign6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Renan Santana
 */
public class LongestRepeatedSubString {
    
    private String str;
    private int lineLength, maxK;
    private int[][] lrsOccurrences;
    
    LongestRepeatedSubString(int maxK){
        this.maxK = maxK;
    }
    
    public void init_lcpOccurrences(int[] lcp){ 
        lrsOccurrences = new int[maxK-1][lcp.length];
        lrsOccurrences[0] = lcp; 
    }
    
    private String trim(String trim){
        String reduced;
        if(trim.length() > 50){
            reduced = trim.substring(0, 19);
            reduced += " ... ";
            reduced += trim.substring(trim.length()-20);
            return reduced;
        }
        return trim;
    }
    
    /* The lrs if() conditions makes the next lcp occurrence based off
     * the current occurrence.
     * First If: current lcp is zero or above lcp is zero then the next lcp 
     * shares lcp of zero.
     * Second If: current lcp of above is greater equal to the current i then
     * next lcp shares the current i lcp.
     * Third Else: next shares the current above lcp
    */
    public void lrsKthOccurence(int[] sa, int k){
        int maxLCP_index = 0, occur_num = 0;
        int[] sort_line_occur = new int[k+2];
        String lrs;
        
        for(int i = 1; i < lrsOccurrences[k].length; i++) {
            if(k < maxK-2){
                if(lrsOccurrences[k][i] == 0 || lrsOccurrences[k][i-1] == 0)
                    lrsOccurrences[k+1][i] = 0;
                else if(lrsOccurrences[k][i-1] >= lrsOccurrences[k][i])
                    lrsOccurrences[k+1][i] = lrsOccurrences[k][i];
                else
                    lrsOccurrences[k+1][i] = lrsOccurrences[k][i-1];
            }
            
            // Get the max occurrence of the current
            if(lrsOccurrences[k][maxLCP_index] < lrsOccurrences[k][i])
                maxLCP_index = i;
        }
        
        // if the max is index 0 there are no max occurrences
        if(maxLCP_index == 0)
            return;
        
        lrs = str.substring(sa[maxLCP_index], sa[maxLCP_index] + lrsOccurrences[k][maxLCP_index]);
        
        System.out.println( "\nLongest sequence that occurs " + (k+2)
                          + " times has length " + lrs.length()
                          + " and is " + trim(lrs));
        
        for (int i = maxLCP_index - (k+1); i <= maxLCP_index; i++) {
            sort_line_occur[occur_num++] = (sa[i] / lineLength + 1);
        }
        
        Arrays.sort(sort_line_occur);
        
        for (int i = 0; i < k+2; i++) {
            System.out.println("Occurrence  #" + (i+1) + 
                               " starts at line "+ sort_line_occur[i]);
        }
        
    }
    
    public String readFile(String file){
        String line;
        BufferedReader br = null;
        StringBuilder strInput = new StringBuilder();
        
        try{
            br = new BufferedReader(new FileReader(file));
            
            line = br.readLine();
            lineLength = line.length();
            
            // Reading the File
            do{
                strInput.append(line);
            }while( (line = br.readLine()) != null );
            
            this.str = strInput.toString();
            return strInput.toString();
            
        }catch(IOException e){ 
            System.out.println("File Missing: " + file); 
        }
        
        return "";
    }
}