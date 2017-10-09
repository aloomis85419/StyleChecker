import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aloom on 10/6/2017.
 */
public class MethodIndentationChecker extends StyleChecker {

    /*
        Checks whether a method is indented to 4 spaces or 1 tab.
     */
    public void methodsIndented(String progLine, int lineCount){
        final int VALID_INDENTATION = 4;
        if(methodRegexMatcher(progLine) == true) {
            if(spaceCounter(progLine) != VALID_INDENTATION){
                errorTrace("Line "+lineCount+": ","Incorrect indentation of method.\n");
            }
        }
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
        regex = "\\s*static\\s*void\\s*main\\s*\\(\\s*String\\s*\\[\\]\\s*[^\\)]*\\)";//does not account for uppercase
        String main =  "main";
        pattern = Pattern.compile(regex);
        patternMatcher = pattern.matcher(inputString);
        if(patternMatcher.find()){
            System.out.println("Main Method Found");
            return true;
        }
        return false;
    }
}
