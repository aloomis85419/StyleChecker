import java.util.Stack;

/**
 * Checks for blanklines between methods.
 * @author : Aaron Loomis
 */
public class BlankLinesBetweenMethodsChecker extends StyleChecker {

    LoopsClosingBraceAlignmentChecker loopsClosingBraceAlignmentChecker = new LoopsClosingBraceAlignmentChecker();
    CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher(); //Regex matcher method cuts down on written code. Consider making the regex matchers their own class.
    int lineOfClosingBrace;

    /**
     *
     * @param currentLine: Line being read.
     * @param lineIndex: Index used to access a specific position in the progLines ArrayList
     * @param lineNum; Line number to print out.
     */
    public void checkForBlankLinesBetweenMethods(String currentLine, int lineIndex, int lineNum){

        if(codeRegexMatcher.methodRegexMatcher(currentLine) == true){
            Stack<String>braceStack = new Stack<>();
            lineOfClosingBrace = loopsClosingBraceAlignmentChecker.lineNumOfLoopClosingBrace(currentLine,braceStack);
            if(!progLines.get(lineOfClosingBrace).isEmpty() || !progLines.get(lineIndex-1).isEmpty()){
                errorTrace("Line "+lineOfClosingBrace+"/"+(lineOfClosingBrace+1)+": ","No blank line between methods. \n");
            }
        }
    }
}
