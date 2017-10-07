import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aloom on 10/6/2017.
 */
public class MethodBraceAlignedLeftChecker extends StyleChecker{
    int alignPosition = 0;
    int matchPosition = 0;
    public void methodBracesAlignedLeft(String currentLine, String nextLine, int lineCount){
        MethodIndentationChecker methodRegexSearcher = new MethodIndentationChecker();
        if (methodRegexSearcher.methodRegexMatcher(currentLine) == true) {
                if(currentLine.contains("{")){
                   errorTrace("Line "+lineCount+": ","Incorrect method brace alignment. Opening brace is on the same line as method declaration.");
                }
                if(nextLine.isEmpty()){
                    errorTrace("Line "+lineCount+1+": ","Blank line between method declaration and opening brace.");
                }
        }
        if(nextLine.contains("{") && methodRegexSearcher.methodRegexMatcher(currentLine) == true){
            Character currentChar;
            for(int i = 0; i < currentLine.length(); i++){
                currentChar = currentLine.charAt(i);
                if(!currentLine.substring(0,3).equals("    ")){
                    errorTrace("Line "+lineCount+": ","Incorrect indentation/spacing of method declaration.");
                    break;
                }
                if(currentChar == ' '){
                    continue;
                }
                else if(currentChar != ' '){
                    alignPosition = i;
                    break;
                }
            }
            System.out.println("Position of first character of method declaration: LineCount: "+lineCount+" Index within Line: "+alignPosition);
            for (int i = 0; i < nextLine.length(); i++){
                currentChar = nextLine.charAt(i);
                if(currentChar == '{'){
                    matchPosition = i;
                    break;
                }
                else{
                    errorTrace("Line "+lineCount+1+": ","Line did not contain opening curly bracket after method declaration.");
                }
            }
            if (alignPosition != matchPosition){
                int spaceDif;
                if(matchPosition > alignPosition){
                    spaceDif = matchPosition - alignPosition;
                    errorTrace("Line "+lineCount+1+": ","Incorrect method brace alignment. Curly braces must be placed under the first letter of a method declaration. "
                            +"Curly brace is "+spaceDif+" spaces to far to the right.");
                }
                else if (matchPosition < alignPosition){
                    spaceDif = matchPosition - alignPosition;
                    errorTrace("Line "+lineCount+1+": ","Incorrect method brace alignment. Curly braces must be placed under the first letter of a method declaration. "
                    +"Curly brace is "+spaceDif+" spaces to far to the left.");
                }
            }
        }
    }
}
