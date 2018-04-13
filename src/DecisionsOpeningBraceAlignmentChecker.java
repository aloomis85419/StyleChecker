import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks that the opening brace of a decision statement is aligned directly under the first character of the statement.
 * @author : Aaron Loomis
 */
public class DecisionsOpeningBraceAlignmentChecker extends StyleChecker {

    CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher();

    /*
            Checks the alignment of the opening brace.
     */
    public void checkLoopBraceAlignment(String progLine,int lineIndex, int lineNum){
        String decType = "";
        int alignPos = 0;
        int bracePos = 0;
        if(progLine.isEmpty()){
            return;
        }
        if(codeRegexMatcher.ifRegexMatcher(progLine)){
            if(progLines.get(lineIndex).contains("{")){
                errorTrace("Line "+lineNum+": ","Opening brace on the same line as "+"IF"+" statement.");
                return;
            }
            if (progLines.get(lineIndex+1).contains("{")) {
                alignPos = getPositionOfDecisionKeyword(progLines.get(lineIndex));
                bracePos = getDecisionOpeningBraceAlignmentPosition(progLines.get(lineIndex+1));
            }
            if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+"IF"+" statement is incorrect.\n");
                return;
            }
        }
        if(codeRegexMatcher.elseifRegexMatcher(progLine)){
            if(progLines.get(lineIndex).contains("{")){
                errorTrace("Line "+lineNum+": ","Opening brace on the same line as "+"ELSE IF"+" statement.");
                return;
            }
            if (progLines.get(lineIndex+1).contains("{")) {
                alignPos = getPositionOfDecisionKeyword(progLines.get(lineIndex));
                bracePos = getDecisionOpeningBraceAlignmentPosition(progLines.get(lineIndex+1));

            }if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+"ELSE IF"+" statement is incorrect.\n");
                return;
            }

        }
        if(codeRegexMatcher.elseRegexMatcher(progLine)){
            if(progLines.get(lineIndex).contains("{")){
                errorTrace("Line "+lineNum+": ","Opening brace on the same line as "+"ELSE"+" statement.");
                return;
            }
            if (progLines.get(lineIndex+1).contains("{")) {
                alignPos = getPositionOfDecisionKeyword(progLines.get(lineIndex));
                bracePos = getDecisionOpeningBraceAlignmentPosition(progLines.get(lineIndex+1));
            }
            if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+"ELSE"+" statement is incorrect.\n");
                return;
            }
        }
        if((codeRegexMatcher.switchRegexMatcher(progLine))){
            if(progLines.get(lineIndex).contains("{")){
                errorTrace("Line "+lineNum+": ","Opening brace on the same line as "+"SWITCH"+" statement.");
                return;
            }
            if (progLines.get(lineIndex+1).contains("{")) {
                alignPos = getPositionOfDecisionKeyword(progLines.get(lineIndex));
                bracePos = getDecisionOpeningBraceAlignmentPosition(progLines.get(lineIndex+1));
            }
            if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+"SWITCH"+" statement is incorrect.\n");
                return;
            }
        }
    }

    /*
        Gets the alignment position of a decision statement.
     */
    public int getDecisionOpeningBraceAlignmentPosition(String progLine){
        Character currentChar;
        for (int i = 0; i < progLine.length(); i++){
            currentChar = progLine.charAt(i);
            if (currentChar == '{'){
                return i;
            }
        }
        return 0;
    }

    /*
       Returns the index of the first character of the keyword.
    */
    public int getPositionOfDecisionKeyword(String currentLine){
        int index = 0;
        while(currentLine.charAt(index)== ' '){
            index++;
        }
        return index;
    }
}
