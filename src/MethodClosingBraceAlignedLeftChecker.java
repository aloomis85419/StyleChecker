import java.util.Stack;

/**
 * Checks the alignment of the closing brace of a method.
 * @author : Aaron Loomis
 */
public class MethodClosingBraceAlignedLeftChecker extends StyleChecker{

    CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher();
    MethodOpeningBraceAlignmentChecker methodOpeningBraceAlignmentChecker = new MethodOpeningBraceAlignmentChecker();
    LoopsClosingBraceAlignmentChecker loopsClosingBraceAlignmentChecker = new LoopsClosingBraceAlignmentChecker();
    int lineNumOfClosingBrace;
    /**
     * Compares the alignment of the closing brace to the alignment of the first letter of method declaration.
     * I retrieves this information from the MethodClosingBraceAlignedLeftChecker class.
     * Uses CodeRegexMatcher class methodRegexMatcher method to identify method declarations.
     */
    public void closingBraceAlignmentCheck(String currentLine, int lineIndex, int lineNum){
        int alignPos;
        int closingBraceIndexPos;
        int lineNumOfClosingBrace;
        Stack<String> braceStack = new Stack<>();
        if(codeRegexMatcher.methodRegexMatcher(currentLine)){
            if(currentLine.contains("{")){
                return; // handled by opening brace checker.
            }
            alignPos = methodOpeningBraceAlignmentChecker.getAlignPosition(currentLine);
            lineNumOfClosingBrace = loopsClosingBraceAlignmentChecker.lineNumOfLoopClosingBrace(currentLine, braceStack);
            closingBraceIndexPos = loopsClosingBraceAlignmentChecker.getIndexPositionOfClosingBrace(lineNumOfClosingBrace);
            if(alignPos != closingBraceIndexPos){
                errorTrace("Line "+(lineNumOfClosingBrace +1)+": ","Incorrect closing-method brace alignment.\n");
            }
        }
    }
    public int getLineOfClosingBrace(){
        return lineNumOfClosingBrace;
    }
}
