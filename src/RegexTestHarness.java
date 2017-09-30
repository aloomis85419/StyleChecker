import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class RegexTestHarness{


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
    String filename;
    ArrayList<String>errorRecord = new ArrayList<>();
    private int lineCount = 0;



//    public int CircleOfNumbers(int n, int firstNumber)
    public static void main(String[]args){

        String inputString = "    public int CircleOfNumbers(int n, int firstNumber)\n";//valid test
        RegexTestHarness regexTestHarness = new RegexTestHarness("C:\\Users\\aloom\\IdeaProjects\\StyleChecker\\src\\aaron_indent.txt");
    }

    public RegexTestHarness(String filename){
        this.filename = filename;
    }


}