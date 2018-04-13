import java.util.Stack;

/**
 * Checks the alignment of the closing brace of a loop statement.
 * @author : Aaron Loomis
 */

public class LoopsClosingBraceAlignmentChecker extends StyleChecker {

    CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher();

    /**
     * Finds the closing brace to various loop types and checks if they are aligned directly under the first letter of their declaration.
     * @param currentLine: Line being read.
     * @param lineIndex: Index used for seaching progLines list if needed.
     * @param lineNum: one more than lineIndex. Used to display line numbers indexed starting at 1.
     */
    LoopsOpeningBraceAlignmentChecker loopsBrace = new LoopsOpeningBraceAlignmentChecker();
    public void closingBraceAlignmentCheck(String currentLine, int lineIndex,int lineNum) {
        int closingBracePos;
        int closingBraceLineNum;
        int alignPos;
        Stack<String> braceStack = new Stack<>();
        if (codeRegexMatcher.whileRegexMatcher(currentLine)) {
            alignPos = getAlignPosition(currentLine);
            closingBraceLineNum = lineNumOfLoopClosingBrace(currentLine,braceStack);
            closingBracePos = getIndexPositionOfClosingBrace(closingBraceLineNum-1);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + (closingBraceLineNum+1) + ":", " Closing brace alignment for " + "WHILE" + " statement is incorrect.\n");
            }
        }
        if (codeRegexMatcher.doRegexMatcher(currentLine)) {
            alignPos = getAlignPosition(currentLine);
            closingBraceLineNum = lineNumOfLoopClosingBrace(currentLine,braceStack);
            closingBracePos = getIndexPositionOfClosingBrace(closingBraceLineNum-1);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + (closingBraceLineNum+1) + ":", " Closing brace alignment for " + "DO" + " statement is incorrect.\n");
            }
        }
        if (codeRegexMatcher.forRegexMatcher(currentLine)) {
            alignPos = getAlignPosition(currentLine);
            closingBraceLineNum = lineNumOfLoopClosingBrace(currentLine,braceStack);
            closingBracePos = getIndexPositionOfClosingBrace(closingBraceLineNum-1);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + (closingBraceLineNum+1) + ":", " Closing brace alignment for " + "FOR" + " statement is incorrect.\n");
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

    public int lineNumOfLoopClosingBrace(String currentLine, Stack<String>braceStack){
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
        String currentLine = progLines.get(lineIndex);
        Character currentChar;
        int index = 0;
        while(currentLine.charAt(index) == ' '){
            index+=1;
        }
        return index;
    }
}