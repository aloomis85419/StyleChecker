import java.io.IOException;
import java.util.Iterator;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aloom on 10/6/2017.
 */
public class MethodBraceAlignedLeftChecker extends StyleChecker{
    int nextLineNum;

    /**
     * This method will compare values alignPosition (the position of the first letter of a method) to matchPosition(position of opening curly brace) to see if they are equal.
     * If the values aren't equal, it will store an error message using errorTrace for printing later on in a report.
     * It is also responsible for checking the alignment of the closing brace of a method. The same action as above is performed for the closing brace.
     * @param currentLine: Line of program being analyzed.
     * @param nextLine: Allows for analysis of the next line (line after current line) which allows for more computational ability.
     * @param lineCount: Passed in from RunChecks, it counts the line number for error printing.
     */
    public void methodBracesAlignedLeft(String currentLine, String nextLine, int lineCount){
        //Worth noting that the methodRegex method could be refactored into a class of its own for use by all of the classes.
        MethodIndentationChecker methodRegexSearcher = new MethodIndentationChecker();
        nextLineNum = lineCount+1;
        int alignPosition = 0;
        int openingBracePos = 0;
        int closingBracePos = 0;
        if (methodRegexSearcher.methodRegexMatcher(currentLine) == true) {
                if(currentLine.contains("{")){
                   errorTrace("Line "+lineCount+": ","Incorrect method brace alignment. Opening brace is on the same line as method declaration.\n");
                }
                if(nextLine.isEmpty()){
                    errorTrace("Line "+nextLineNum+": ","Blank line between method declaration and opening brace.\n");
                }
        }
        if(nextLine.contains("{") && methodRegexSearcher.methodRegexMatcher(currentLine) == true){
            openingBracePos = getMatchPosition(nextLine);
            alignPosition = getAlignPosition(currentLine);

            if (alignPosition != openingBracePos){
                    errorTrace("Line "+nextLineNum+": ","Incorrect opening-method brace alignment.\n");
            }
        }
    }

    /**
     * This method find the fist letter of a method declaration.
     * @param currentLine: Line of program to analyze.
     * @return: The position of the first letter of a method declaration.
     */
    public Integer getAlignPosition(String currentLine){
        Character currentChar;
        Integer alignPosition = 0;
        for(int i = 0; i < currentLine.length(); i++){
            currentChar = currentLine.charAt(i);
            if(currentChar == ' '){
                continue;
            }
            else if(currentChar != ' '){
                alignPosition = i;
                break;
            }
        }
       return  alignPosition;
    }

    /**
     * Finds the position of the opening curly brace.
     * @param nextLine: The line after currentLine;
     * @return: The position of the opening curly brace
     */
    public Integer getMatchPosition(String nextLine) {
        Character currentChar;
        Integer matchPosition = 0;
        for (int i = 0; i < nextLine.length(); i++) {
            currentChar = nextLine.charAt(i);
            if (currentChar == '{') {
                matchPosition = i;
                break;
            }
        }
        return matchPosition;
    }



    }
