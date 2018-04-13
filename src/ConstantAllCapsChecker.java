import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks for all letters of a constant being capitalized
 * @author : Aaron Loomis
 */
public class ConstantAllCapsChecker extends StyleChecker{

    int lineNum;
    CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher();

    /**
     * Looks to see if constant variables are all caps.
     */
    public void constantsAllCaps(String progLine, int lineCount){
        String typeName;
        lineNum = lineCount;
        if(codeRegexMatcher.constantRegexMatcher(progLine)){
            typeName =  extractConstant(progLine);
            if(isAllCaps(typeName) == false){
                errorTrace("Line "+lineCount+": ","Constant is not all caps or contains invalid characters. Valid characters are capital letters or '_'.\n");
            }
        }
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
     * @return: true if the single character matches the regex.
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
            if(!isAlpha(c.toString()) || c =='_'){
                continue;
            }
            else{
                constant += progLine.charAt(i);
            }
        }
        return constant;
    }
}