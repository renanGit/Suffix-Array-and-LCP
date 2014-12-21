package assign6;

/**
 *
 * @author Renan Santana
 */
public class Assign6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SuffixArray sa = new SuffixArray();
        
        for (String arg : args) {
            System.out.println("******  File: " + arg + "  ******");
            sa.run(arg);
            System.out.println("******  END  ******\n");
        }
    }
    
}

