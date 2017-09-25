





import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * StyleChecker is a program that checks for a set list of programming style errors.
 * @author : Aaron C. Loomis
 * @version : 1.0
 */
public class StyleChecker {

    private boolean methodIndented;
    private boolean decisionsIndented;
    private boolean loopsIndented;
    private boolean variablesIndented;
    private boolean spacesAroundBinary;
    private boolean methodBracesAlignedLeft;
    private boolean blanklineBetweenMethods;
    private boolean methodsCapitalized;
    private boolean constantsAllCaps;
    private boolean classCapitalized;
    private boolean magicNumber;
    private boolean noBlanklinesBetweenDecisions;
    private boolean decisionsCurlys;
    private boolean loopsCurlys;
    BufferedReader programReader;
    FileReader fr;


    /**Creates an instance of the style checker with a given file name to check for style errors.
     */
    public StyleChecker(String fileName ) {
        try{
            this.fr = new FileReader(fileName);
            this.programReader = new BufferedReader(fr);
        }catch (FileNotFoundException f){
            f.getMessage();
        }
    }

    public boolean methodsIndented() {

        return false;
    }

    public boolean decisonStatementsIndented(){

        return false;
    }

     public boolean loopsIndented(){

         return false;
     }

     public boolean spacedBinaryOperators(){

         return false;
     }

    public boolean methodBracesAlignedLeft(){

        return false;
    }

    public boolean blanklineBetweenMethods(){

        return false;
    }

    public boolean classNameCapitalized(){

        return false;
    }

    public boolean  methodNamesLowerCase(){

        return false;
    }

    public boolean constantsAllCaps(){

        return false;
    }

    public boolean noMagicNumbers(){

        return false;
    }

    public boolean blankLinesBetweenMethods(){

        return false;
    }

    public boolean blankLinesBetweenDecisions(){

        return false;
    }

    public boolean isBlanklineBetweenLoops(){

        return false;
    }

    public boolean decisionsHaveCurlys(){

        return false;
    }

    public boolean loopsHaveCurlys(){

        return false;
    }

    public void outputFileReport(){

    }


    public static void main(String[]args)
    {

    }

}
