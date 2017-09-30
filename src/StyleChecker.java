

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    String filename;
    ArrayList<String> errorRecord = new ArrayList<>();
    private int lineCount = 0;

    /**Creates an instance of the style checker with a given file name to check for style errors.
     */
    public StyleChecker(String filename){
        this.filename = filename;
    }

    /**
     *A method that compares the indentation of a programs method relative to the containing class.
     * Valid indentation of methods is considered to be 8 spaces in all cases. I dont consider indentation of methods when there is an inner class.
     * @return: False if no style errors of this type were found.
     */
    public void methodsIndented() {
        String progLine = null;
        final int VALID_INDENTATION = 4;
        try {
            fr= new FileReader(filename);
            programReader = new BufferedReader(fr);
            while ((progLine = programReader.readLine()) != null) {
                lineCount += 1;
                if(methodRegexMatcher(progLine) == true) {
                    System.out.println("Spacecount: "+spaceCounter(progLine));
                    if(spaceCounter(progLine) != VALID_INDENTATION){
                        System.out.print(errorTrace("Incorrect indentation of method.\n","Line "+lineCount+": "));
                    }
                }
            }
        }catch (FileNotFoundException f){
            System.out.println("File not found.");
            f.printStackTrace();
        }catch(IOException fileReadError){
            System.out.println("Error reading file.");
            fileReadError.printStackTrace();
        }
        lineCount = 0; //reset line count for use by other checks.
    }

    /**
     *A method that counts the spaces at the beginning of a line of code. It exits upon finding one occurence of a character that is not a space.
     * A tab is considered as 4 spaces. Does not consider methods of inner classes.
     * @param progLine: A line of a program passed in as a string.
     * @return: the number of spaces at the beginning of a line of code.
     */
    public int spaceCounter(String progLine){
        int spaceCount = 0;
        for(int i = 0; i < progLine.length(); i++)
        {
            if(progLine.charAt(i) == '\t'){
                spaceCount += 4;
                continue;
            }
            else if(progLine.charAt(i) == ' '){
                spaceCount += 1;
                continue;
            }
            if(progLine.charAt(i) != ' '){
                break;
            }
        }
        return spaceCount;
    }

    /**
     *Indentifies a method declaration based on a specific regex. Searches an entire line of the test program for this regex.
     * @param : Input string equates to an entire line of a text file (a program in text file format for our purposes)
     */
    public boolean methodRegexMatcher(String inputString){
        String regex = "(public?|private?|protected?)\\s+(int|boolean|char|long|byte|float|double|\\w)\\s+(\\w*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            System.out.println("\nFOUND A METHOD");
            System.out.println(inputString);
            return true;
        }
        regex = "\\s*static\\s*void\\s*main\\s*\\(\\s*String\\s*\\[\\]\\s*[^\\)]*\\)";
        pattern = Pattern.compile(regex);
        patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            System.out.println("FOUND A MAIN METHOD");
            System.out.println(inputString);
            return true;
        }
        return false;
    }

    /**
     * A method that looks to see if decisions statements are indented relative to the methods that contain them.
     * @return: False if no style errors of this type were found.
     */
    public boolean decisonStatementsIndented(){

        return false;
    }

    /**
     * A method that looks to see if loop structures are indented relative to the methods that contain them.
     * @return: False if no style errors of this type were found.
     */
     public boolean loopsIndented(){

         return false;
     }

    /**
     * A method that looks to see if binary operators have a single space surrounding them.
     * @return: False if no style errors of this type were found.
     */
     public boolean spacedBinaryOperators(){

         return false;
     }

    /**
     * A method that looks to see braces are aligned under the method declaration.
     * @return: False if no style errors of this type were found.
     */
    public boolean methodBracesAlignedLeft(){

        return false;
    }

    /**
     * A method that looks to see if blank lines are between methods in a class.
     * @return: False if no style errors of this type were found.
     */
    public boolean blanklineBetweenMethods(){

        return false;
    }

    /**
     * A method that looks to see if the first letter of a class name is capitalized.
     * @return: False if no style errors of this type were found.
     */
    public void classNameCapitalized(){
        String progLine = null;
        Character capCheck;
        String classId = "class";
        try {
            fr= new FileReader(filename);
            programReader = new BufferedReader(fr);
            while ((progLine = programReader.readLine()) != null) {
                lineCount += 1;
                if(progLine.contains(classId)){
                    String className;
                   int decStartPOS = progLine.indexOf(classId);
                   int decEndPOS = (decStartPOS) + classId.length();
                   className = progLine.substring(decEndPOS, progLine.length());
                   className = extractClassName(className);
                   capCheck = className.charAt(0);
                   System.out.println("Class name: "+className);
                   if(!capCheck.toString().equals(capCheck.toString().toUpperCase()) ){
                       System.out.print(errorTrace("Classname not capitalized.\n","Line "+lineCount+": "));
                       System.out.println(className);
                   }
                }
            }
        }catch (FileNotFoundException f){
            System.out.println("File not found.");
            f.printStackTrace();
        }catch(IOException fileReadError){
            System.out.println("Error reading file.");
            fileReadError.printStackTrace();
        }
        lineCount = 0;
    }

    /**
     * This method extracts the class name from a substring of the line containing the class name.
     * @param name; A section of a program line containing the class name to be extracted.
     * @return; Returns the class name only.
     */
    public String extractClassName(String name){
        String extracted = "";
        for (int i = 0; i < name.length(); i++){

            if(name.charAt(i) == ' '){
                continue;
            }
            else{
                extracted += name.charAt(i);
        }
        }
        return extracted;
    }


    /**
     * A method that looks to see if method names are lowercase.
     * @return: False if no style errors of this type were found.
     */
    public boolean  methodNamesLowerCase(){

        return false;
    }

    /**
     * A method that looks to see if constant variables are all caps.
     * @return: False if no style errors of this type were found.
     */
    public boolean constantsAllCaps(){

        return false;
    }

    /**
     * A method that looks to see if the program contains a magic number.
     * @return: False if no style errors of this type were found.
     */
    public boolean noMagicNumbers(){

        return false;
    }

    /**
     * A method that looks to see if there are blank lines between decisions.
     * @return: False if no style errors of this type were found.
     */
    public boolean blankLinesBetweenDecisions(){

        return false;
    }

    /**
     * A method that looks to see if there are blank lines between loops
     * @return: False if no style errors of this type were found.
     */
    public boolean isBlanklineBetweenLoops(){

        return false;
    }

    /**
     * A method that looks to see if decision statements have curly brackets.
     * @return: False if no style errors of this type were found.
     */
    public boolean decisionsHaveCurlys(){

        return false;
    }

    /**
     * A method that looks to see if loops structures have curly brackets.
     * @return: False if no style errors of this type were found.
     */
    public boolean loopsHaveCurlys(){

        return false;
    }

    /**
     * Outputs a collective style error report to a file. This will replace errorTrace() in final version.
     */
    public void outputFileReport(){
        for (Iterator<String> itr = errorRecord.iterator(); itr.hasNext();) {
            String errorMsg = itr.next();
            System.out.println(errorMsg);
        }
    }

    /**
     * Concatenates a message with the line number and type of error.
     * @return: Message that contains the lines number and the type of error.
     */
    public String errorTrace(String styleErrorMessage, String lineNumber){
        errorRecord.add(styleErrorMessage+" "+lineNumber);
        return lineNumber+styleErrorMessage;
    }


    public static void main(String[]args)
    {
        StyleChecker styleChecker = new StyleChecker("C:\\Users\\aloom\\IdeaProjects\\StyleChecker\\src\\aaron_indent.txt");
        styleChecker.methodsIndented();
        styleChecker.classNameCapitalized();
    }
}
