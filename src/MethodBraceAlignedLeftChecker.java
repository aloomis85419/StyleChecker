import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aloom on 10/6/2017.
 */
public class MethodBraceAlignedLeftChecker extends StyleChecker{
    //WORK IN PROGRESS.
    int alignPosition = 0;
    int matchPosition = 0;
    int nextLineNum;
    public void methodBracesAlignedLeft(String currentLine, String nextLine, int lineCount){
        MethodIndentationChecker methodRegexSearcher = new MethodIndentationChecker();
        nextLineNum = lineCount+1;
        if (methodRegexSearcher.methodRegexMatcher(currentLine) == true) {
                if(currentLine.contains("{")){
                   errorTrace("Line "+lineCount+": ","Incorrect method brace alignment. Opening brace is on the same line as method declaration.\n");
                }
                if(nextLine.isEmpty()){
                    errorTrace("Line "+nextLineNum+": ","Blank line between method declaration and opening brace.\n");
                }
        }
        if(nextLine.contains("{") && methodRegexSearcher.methodRegexMatcher(currentLine) == true){
            Character currentChar;
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
            for (int i = 0; i < nextLine.length(); i++){
                currentChar = nextLine.charAt(i);
                if(currentChar == '{'){
                    matchPosition = i;
                    break;
                }
            }
            if (alignPosition != matchPosition){
                int spaceDif;
                if(matchPosition > alignPosition){
                    spaceDif = matchPosition - alignPosition;
                    errorTrace("Line "+nextLineNum+": ","Incorrect method brace alignment. Curly braces must be placed under the first letter of a method declaration.\n"
                            +"Curly brace is "+spaceDif+" spaces to far to the right.");
                }
                else if (matchPosition < alignPosition){
                    spaceDif = alignPosition - matchPosition;
                    errorTrace("Line "+nextLineNum+": ","Incorrect method brace alignment. Curly braces must be placed under the first letter of a method declaration.\n"
                    +"Curly brace is "+spaceDif+" spaces to far to the left.");
                }
            }
        }
    }
}
