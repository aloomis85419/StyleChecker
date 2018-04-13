import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class that allows for patterns in a program to be matched to a regex.
 * @author : Aaron Loomis
 */
public class CodeRegexMatcher {

    /**
     *Indentifies a method declaration based on a specific regex. Searches an entire line of the test program for this regex.
     * @param : Input string equates to an entire line of a text file (a program in text file format for our purposes)
     */
    public boolean methodRegexMatcher(String inputString){
        String regex = "(public|private|protected)\\s+(static?|abstract?)\\s+(String|void|int|boolean|char|long|byte|float|double|)\\s+(\\w*)\\s*(\\()";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            return true;
        }
        regex = "\\s*static\\s*void\\s*main\\s*\\(\\s*String\\s*\\[\\]\\s*[^\\)]*\\)";//does not account for uppercase
        String main =  "main";
        pattern = Pattern.compile(regex);
        patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            return true;
        }
        regex = "(public|private|protected)\\s+((static?|abstract?)\\s+|(String|void|int|boolean|char|long|byte|float|double|))\\s+(\\w*)\\s*(\\()";//does not account for uppercase
        pattern = Pattern.compile(regex);
        patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    public boolean constructorRegexMatcher(String inputString){
        String regex = "(public)\\s+([A-Z]|[a-z])*\\s*(\\()";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    public boolean methodCallRegexMatcher(String inputString){
        String regex = "(_*)(|[A-Z]+|[a-z]+|[1-9]*)(_*)\\s*(\\(\\s*\\))\\s*\\;";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    public boolean methodLowerCaseRegexMatcher(String inputString){
        String regex = "(_*)([a-z]+|[1-9]*)(_*)\\s*(\\(\\s*\\))\\s*\\;";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    public boolean classRegexMatcher(String inputString){
        String regex = "(public?|private?)(\\s+)(class{1})\\s+(\\w+|\\d*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    /**
     * Compares a line of the program to a custom regex for a constant.
     * @param inputString;A line of the program to be matched by the constant regex.
     * @return; True if the line matches the regular expression for a constant.
     */
    public boolean constantRegexMatcher(String inputString){
        String regex = "(final)\\s+(String|int|boolean|char|long|byte|float|double|\\w)\\s+(\\w*)\\s*(=)\\s+(\\w*|\\d*|.)";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    /*
        All of the following methods use a regex to match a specific decision header.
     */
    public boolean ifRegexMatcher(String currentLine){
        String regex = "(if)\\s*(\\()";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(currentLine);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    public boolean elseifRegexMatcher(String currentLine){
        String regex = "(else if)\\s*(\\()";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(currentLine);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    public boolean elseRegexMatcher(String currentLine){
        String regex = "(else)\\s*";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(currentLine);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    public boolean switchRegexMatcher(String currentLine){
        String regex = "(switch)\\s*(\\()";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(currentLine);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    public boolean forRegexMatcher(String currentLine){
        String regex = "(for)\\s*(\\()";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(currentLine);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    public boolean whileRegexMatcher(String currentLine){
        String regex = "(while)\\s*(\\()";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(currentLine);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }

    public boolean doRegexMatcher(String currentLine){
        String regex = "(do)\\s*(\\()";
        Pattern pattern = Pattern.compile(regex);
        Matcher patternMatcher = pattern.matcher(currentLine);
        if(patternMatcher.find()){
            return true;
        }
        return false;
    }
}
