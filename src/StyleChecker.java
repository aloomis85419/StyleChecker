

import java.io.*;
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
    BufferedReader programReader;
    FileReader fr;
    String filename;
    static ArrayList<String> errorRecord = new ArrayList<>();
    ArrayList<String>progLines = new ArrayList<>();
    private int lineCount = 0;

    /**Creates an instance of the style checker with a given file name to check for style errors.
     */
    public StyleChecker(String filename){
        this.filename = filename;
    }

    /**
     *Compares the indentation of a program method relative to the containing class.
     * Valid indentation of methods is considered to be 8 spaces in all cases. I dont consider indentation of methods when there is an inner class.
     * @return: False if no style errors of this type were found.
     */
    public void methodsIndented() {
        String progLine;
        final int VALID_INDENTATION = 4;
        try {
            fr= new FileReader(filename);
            programReader = new BufferedReader(fr);
            while ((progLine = programReader.readLine()) != null) {
                lineCount += 1;
                if(methodRegexMatcher(progLine) == true) {
                    if(spaceCounter(progLine) != VALID_INDENTATION){
                        errorTrace("Incorrect indentation of method.\n","Line "+lineCount+": ");
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
     *Counts the spaces at the beginning of a line of code. It exits upon finding one occurence of a character that is not a space.
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
            return true;
        }
        regex = "\\s*static\\s*void\\s*main\\s*\\(\\s*String\\s*\\[\\]\\s*[^\\)]*\\)";
        pattern = Pattern.compile(regex);
        patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    /**
     * Looks to see if decisions statements are indented relative to the methods that contain them.
     * @return: False if no style errors of this type were found.
     */
    public boolean decisonStatementsIndented(){

        return false;
    }

    /**
     * Looks to see if loop structures are indented relative to the methods that contain them.
     * @return: False if no style errors of this type were found.
     */
     public boolean loopsIndented(){

         return false;
     }

    /**
     * Looks to see if binary operators have a single space surrounding them.
     * @return: False if no style errors of this type were found.
     */
     public boolean spacedBinaryOperators(){

         return false;
     }

    /**
     * Looks to see braces are aligned under the method declaration.
     * @return: False if no style errors of this type were found.
     */
    public boolean methodBracesAlignedLeft(){

        return false;
    }

    /**
     * Looks to see if blank lines are between methods in a class.
     * @return: False if no style errors of this type were found.
     */
    public boolean blanklineBetweenMethods(){

        return false;
    }

    /**
     * Looks to see if the first letter of a class name is capitalized.
     * @return: False if no style errors of this type were found.
     */
    public void classNameCapitalized(){
        String progLine;
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
                   if(!capCheck.toString().equals(capCheck.toString().toUpperCase()) ){
                       errorTrace("Class name not capitalized.\n","Line "+lineCount+": ");
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
     * Extracts the class name from a substring of the line containing the class name.
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
     * Looks to see if method names are lowercase.
     * @return: False if no style errors of this type were found.
     */
    public boolean  methodNamesLowerCase(){
        return false;
    }

    /**
     * Looks to see if constant variables are all caps.
     */
    public void constantsAllCaps(){
        String progLine;
        String typeName;
        try {
            fr= new FileReader(filename);
            programReader = new BufferedReader(fr);
            while ((progLine = programReader.readLine()) != null) {
                lineCount += 1;
                if(constantRegexMatcher(progLine)){
                    typeName = progLine.substring(getEndPositionOfTypeIdentifier(progLine), progLine.length());
                    typeName =  extractConstant(progLine);
                    if(isAllCaps(typeName) == false){
                        errorTrace("Constant is not all caps or contains invalid characters. Valid characters are capital letters or '_'.\n","Line "+lineCount+": ");
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
     * Compares a line of the program to a custom regex for a constant.
     * @param inputString;A line of the program to be matched by the constant regex.
     * @return; True if the line matches the regular expression for a constant.
     */
    public boolean constantRegexMatcher(String inputString){
        String regex = "(((final|private?|protected?|static?))\\s+(final|private?|protected?|static?))\\s+(int|boolean|char|long|byte|float|double|\\w)\\s+(\\w*)\\s*(=)\\s+(\\w*|\\d*|.)";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            System.out.println("FOUND A CONSTANT");
            return true;
        }
        return false;
    }

    /**
     * Checks whether the fully extracted constant is completely capitalized.
     * @param constant: The name of a constant that has been fully extracted in String form for capitalization analysis.
     * @return: True if constant is all caps
     */
    private boolean isAllCaps(String constant){
        if(!constant.equals(constant.toUpperCase())){
            return false;
        }
        return true;
    }

    /**
     * Checks to see which constant identifier is contained in the line and gets its starting index position within the line of the program.
     * @param progLine: Line of the program to analyze
     * @return: The start position of a constant type identifier ie int double so on...
     */
    private int getStartPosOfTypeIdentifier(String progLine){
        int typeStartPOS = 0;
        if(progLine.contains("int")){
            typeStartPOS = progLine.indexOf("int");
        }
        if(progLine.contains("double")){
            typeStartPOS = progLine.indexOf("double");
        }
        if(progLine.contains("float")){
            typeStartPOS = progLine.indexOf("float");
        }
        if(progLine.contains("long")){
            typeStartPOS = progLine.indexOf("long");
        }
        if(progLine.contains("String")){
            typeStartPOS = progLine.indexOf("String");
        }
        if(progLine.contains("Character")){
            typeStartPOS = progLine.indexOf("Character");
        }
        if(progLine.contains("char")){
            typeStartPOS = progLine.indexOf("char");
        }
        if(progLine.contains("Integer")){
            typeStartPOS = progLine.indexOf("Integer");
        }
        if(progLine.contains("Double")){
            typeStartPOS = progLine.indexOf("Double");
        }
        if(progLine.contains("Float")){
            typeStartPOS = progLine.indexOf("Float");
        }
        if(progLine.contains("Long")){
            typeStartPOS = progLine.indexOf("Long");
        }
        if(progLine.contains("boolean")){
            typeStartPOS = progLine.indexOf("boolean");
        }
        if(progLine.contains("Boolean")){
            typeStartPOS = progLine.indexOf("Boolean");
        }
        if(progLine.contains("byte")){
            typeStartPOS = progLine.indexOf("byte");
        }
        if(progLine.contains("Byte")){
            typeStartPOS = progLine.indexOf("Byte");
        }
        if(progLine.contains("short")){
            typeStartPOS = progLine.indexOf("short");
        }
        if(progLine.contains("Short")){
            typeStartPOS = progLine.indexOf("Short");
        }
        return typeStartPOS;
    }

    /**
     * Checks to see which constant identifier is contained in the line and gets its ending index position within the line of the program.
     * @param progLine: Line of the program to analyze
     * @return: The end position of a constant type identifier ie int double so on...
     */
    private int getEndPositionOfTypeIdentifier(String progLine){
        int typeEndPOS = 0;
        String type = "";
        if(progLine.contains("int")){
            type = "int";
            typeEndPOS = progLine.indexOf("int") + type.length();
        }
        if(progLine.contains("double")){
            type = "double";
            typeEndPOS = progLine.indexOf("double") + type.length();
        }
        if(progLine.contains("float")){
            type = "float";
            typeEndPOS = progLine.indexOf("float") + type.length();
        }
        if(progLine.contains("long")){
            type = "long";
            typeEndPOS = progLine.indexOf("long") + type.length();
        }
        if(progLine.contains("String")){
            type = "String";
            typeEndPOS = progLine.indexOf("String") + type.length();
        }
        if(progLine.contains("Character")){
            type = "Character";
            typeEndPOS = progLine.indexOf("Character") + type.length();
        }
        if(progLine.contains("char")){
            type = "char";
            typeEndPOS = progLine.indexOf("char") + type.length();
        }
        if(progLine.contains("Integer")){
            type = "Integer";
            typeEndPOS = progLine.indexOf("Integer") + type.length();
        }
        if(progLine.contains("Double")){
            type = "Double";
            typeEndPOS = progLine.indexOf("Double") + type.length();
        }
        if(progLine.contains("Float")){
            type = "Float";
            typeEndPOS = progLine.indexOf("Float") + type.length();
        }
        if(progLine.contains("Long")){
            type = "Long";
            typeEndPOS = progLine.indexOf("Long") + type.length();
        }
        if(progLine.contains("boolean")){
            type = "boolean";
            typeEndPOS = progLine.indexOf("boolean") + type.length();
        }
        if(progLine.contains("Boolean")){
            type = "Boolean";
            typeEndPOS = progLine.indexOf("Boolean") + type.length();
        }
        if(progLine.contains("byte")){
            type = "byte";
            typeEndPOS = progLine.indexOf("byte") + type.length();
        }
        if(progLine.contains("Byte")){
            type = "Byte";
            typeEndPOS = progLine.indexOf("Byte") + type.length();
        }
        if(progLine.contains("short")){
            type = "short";
            typeEndPOS = progLine.indexOf("short") + type.length();
        }
        if(progLine.contains("Short")){
            type = "Short";
            typeEndPOS = progLine.indexOf("Short") + type.length();
        }
        return typeEndPOS;
    }

    /**
     * Analyzes characters to see if they are letters.
     * @param s: A single character as a string that is to be matched to a regex.
     * @return: true if the single character mateches the regex.
     */
    public boolean isAlpha(String s) {
        return s.matches("[a-z|A-Z]");
    }

    /**
     * Returns the name of the constant without anything else from the line of the program being analyzed.
     * @param progLine; The line of the program currently being analyzed.
     * @return: Fully extracted constant with no extras.
     */
    private String extractConstant(String progLine){
        String constant = "";
        for (int i = getEndPositionOfTypeIdentifier(progLine); i < progLine.length(); i++){
            Character c = progLine.charAt(i);
            if(!isAlpha(c.toString()) && c != '_'){
                continue;
            }
            else{
                constant += progLine.charAt(i);
            }
        }
        System.out.println("Extracted constant: "+constant);
        return constant;
    }

    /**
     * Looks to see if decision statements have curly brackets. It only looks for the opening curly because it assumes the program compiles without error.
     * It uses a simple method to check for if statements. Therefore comments matching the "if(" pattern will cause error messages.
     */
    public void decisionsHaveCurlys(){
        String progLine;
        int nextIndex =  1;
        String progNextLine;
        try {
            fr= new FileReader(filename);
            programReader = new BufferedReader(fr);
            while ((progLine = programReader.readLine()) != null) {
                lineCount += 1;
                progNextLine = progLines.get(nextIndex);
                determineDecisionErrorMessage(progLine, progNextLine);
                if(nextIndex == (progLines.size()-1)){
                    break;
                }
                nextIndex++;
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
     * Prints various messages relating to which kind of decision structure has the no curly bracket error.
     * @param progLine: Current line being read.
     * @param progNextLine: Line in next sequential order to progLine(current line).
     */
    private void determineDecisionErrorMessage(String progLine, String progNextLine) {
        if(progLine.contains("if(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Missing curly bracket after IF statement.\n","Line "+lineCount+": ");
            }
        }
        else if(progLine.contains("switch(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Missing curly bracket after SWITCH statement.\n","Line "+lineCount+": ");
            }
        }
        else if(progLine.contains("else if(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Missing curly bracket after ELSE IF statement.\n","Line "+lineCount+": ");
            }
        }
       else if(progLine.contains("else")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Missing curly bracket after ELSE statement.\n","Line "+lineCount+": ");
            }
        }
    }

    /**
     * Looks to see if loops structures have curly brackets.
     * @return: False if no style errors of this type were found.
     */
    public void loopsHaveCurlys(){
        String progLine;
        int nextIndex =  1;
        String progNextLine;
        try {
            fr= new FileReader(filename);
            programReader = new BufferedReader(fr);
            while ((progLine = programReader.readLine()) != null) {
                lineCount += 1;
                progNextLine = progLines.get(nextIndex);
                determineLoopErrorType(progLine, progNextLine);
                if(nextIndex == (progLines.size()-1)){
                    break;
                }
                nextIndex++;
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
     * Prints various messages relating to which kind of loop structure has the no curly bracket error.
     * @param progLine: Current line being read.
     * @param progNextLine: Line in next sequential order to progLine(current line).
     */
    private void determineLoopErrorType(String progLine, String progNextLine) {
        if(progLine.contains("while(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Missing curly bracket after WHILE statement.\n","Line "+lineCount+": ");
            }
        }
        if(progLine.contains("for(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Missing curly bracket after FOR statement.\n","Line "+lineCount+": ");
            }
        }
        if(progLine.contains("do(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Missing curly bracket after DO statement.\n","Line "+lineCount+": ");
            }
        }
    }

    /**
     * Outputs a collective style error report to a file.
     */
    public void outputFileReport(String outputFile){
        try{
            FileWriter fileWriter = new FileWriter(outputFile);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf("AUTHOR: AARON LOOMIS\nSTYLE CHECKER Vers 1.0\n");
            if(hasNoErrors()){
                printWriter.print("No traceable style errors were found.");
            }
            for (Iterator<String> itr = errorRecord.iterator(); itr.hasNext();) {
                String errorMsg = itr.next();
                printWriter.printf(errorMsg);
            }
            printWriter.close();
        }catch (IOException e){
            System.out.println("Error writing to file");
        }
    }

    /**
     * Concatenates a message with the line number and type of error.
     * @return: Message that contains the lines number and the type of error.
     */
    public void errorTrace(String styleErrorMessage, String lineNumber){
        String errorMsg = lineNumber+styleErrorMessage;
        errorRecord.add(errorMsg);
    }

    public void fillListWithProgLines(){
        String progLine;
        try {
            fr= new FileReader(filename);
            programReader = new BufferedReader(fr);
            while ((progLine = programReader.readLine()) != null) {
                progLines.add(progLine);
                lineCount += 1;
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

    private boolean hasNoErrors(){
        if(errorRecord.isEmpty()){
            return true;
        }
        return false;
    }


    public static void main(String[]args)
    {
        StyleChecker styleChecker = new StyleChecker("C:\\Users\\aloom\\IdeaProjects\\StyleChecker\\src\\aaron_indent.txt");
        String outputFileName = "StyleCheckResults.txt";
        styleChecker.fillListWithProgLines();
        styleChecker.classNameCapitalized();
        styleChecker.methodsIndented();
        styleChecker.decisionsHaveCurlys();
        styleChecker.loopsHaveCurlys();
        styleChecker.constantsAllCaps();
        styleChecker.outputFileReport(outputFileName);
    }
}
