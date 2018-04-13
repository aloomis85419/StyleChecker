import java.awt.*;
import java.util.Stack;

/**
 * Checks the laignment of the closing brace of a decision statement.
 * @author : Aaron Loomis
 */
public class DecisionStatementsClosingBraceAlignmentChecker extends StyleChecker {

        CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher();
    /*
        Prints an error message if the closing brace
        is out of alignment with first character of the decision statement.
     */
    public void closingBraceAlignmentCheck(String currentLine, int lineIndex,int lineNum) {
        int closingBracePos;
        int closingBraceLineNum;
        int alignPos;
        Stack<String>braceStack = new Stack<>();
        if (codeRegexMatcher.ifRegexMatcher(currentLine)) {
            alignPos = getAlignPosition(currentLine);
            closingBraceLineNum = lineNumOfDecisionClosingBrace(currentLine,braceStack);
            closingBracePos = getIndexPositionOfClosingBrace(closingBraceLineNum-1);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + (closingBraceLineNum+1) + ":", " Closing brace alignment for " + "IF" + " statement is incorrect.\n");
            }
        }
        if (codeRegexMatcher.elseifRegexMatcher(currentLine)) {
            alignPos = getAlignPosition(currentLine);
            closingBraceLineNum = lineNumOfDecisionClosingBrace(currentLine,braceStack);
            closingBracePos = getIndexPositionOfClosingBrace(closingBraceLineNum-1);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + (closingBraceLineNum+1) + ":", " Closing brace alignment for " + "ELSE IF" + " statement is incorrect.\n");
            }
        }
        if (codeRegexMatcher.elseRegexMatcher(currentLine)) {
            alignPos = getAlignPosition(currentLine);
            closingBraceLineNum = lineNumOfDecisionClosingBrace(currentLine,braceStack);
            closingBracePos = getIndexPositionOfClosingBrace(closingBraceLineNum-1);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + (closingBraceLineNum+1) + ":", " Closing brace alignment for " + "ELSE" + " statement is incorrect.\n");
            }
        }
        if (codeRegexMatcher.switchRegexMatcher(currentLine)) {
            alignPos = getAlignPosition(currentLine);
            closingBraceLineNum = lineNumOfDecisionClosingBrace(currentLine,braceStack);
            closingBracePos = getIndexPositionOfClosingBrace(closingBraceLineNum-1);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + (closingBraceLineNum+1) + ":", " Closing brace alignment for " + "SWITCH" + " statement is incorrect.\n");
            }
        }
    }

    public int getAlignPosition(String currentLine){
        Character currentChar;
        int index = 0;
        for (int i = 0; i < currentLine.length(); i++){
            currentChar = currentLine.charAt(i);
            if (currentChar == ' '){
                index+=1;
                continue;
            }
            else {

                return index;
            }
        }
        return index;
    }

    public boolean hasOpeningBrace(String currentLine){
        if(currentLine.contains("{")){
            return true;
        }
        return false;
    }

    public boolean hasClosingBrace(String currentLine){
        if(currentLine.contains("}")){
            return true;
        }
        return false;
    }

    public int lineNumOfDecisionClosingBrace(String currentLine, Stack<String>braceStack){
        int indexOfCurrentLine = progLines.indexOf(currentLine);
        String nextLine = progLines.get(indexOfCurrentLine+1);
        braceStack.push("$");
        while(!braceStack.empty()){
            if(hasClosingBrace(nextLine))braceStack.pop();
            if(hasOpeningBrace(nextLine))braceStack.push("{");
            indexOfCurrentLine+=1;
            nextLine = progLines.get(indexOfCurrentLine+1);
            if (braceStack.peek() == "$"){
                braceStack.pop();
            }
        }
        return indexOfCurrentLine;
    }

    public int getIndexPositionOfClosingBrace(int lineIndex){
        String currentLine = progLines.get(lineIndex+1);
        Character currentChar;
        int index = 0;
        while(currentLine.charAt(index) == ' '){
            index+=1;
        }
        return index;
    }
}
