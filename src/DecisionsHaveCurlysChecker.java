/**
 * Checks for single line decision statements.
 * @author : Aaron Loomis
 */
public class DecisionsHaveCurlysChecker extends StyleChecker {

    CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher();
    /**
     * Prints various messages relating to which kind of decision structure has the no curly bracket error. This specifically checks for one line if statements.
     * Missing curly brackets in other cases are a compile time error and aren't checked for.
     */
    public void determineDecisionErrorMessage(String currentLine, int lineIndex,int lineNum) {
        if(codeRegexMatcher.ifRegexMatcher(currentLine)){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after IF statement.\n");
            }
        }
        if(codeRegexMatcher.elseifRegexMatcher(currentLine)){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after IF ELSE statement.\n");
            }
        }
        if(codeRegexMatcher.elseRegexMatcher(currentLine)){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{")){
                System.out.println("progline + 1 is decisions have curlys checker "+progLines.get(lineIndex+1));
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after ELSE statement.\n");
            }
        }
        if(codeRegexMatcher.switchRegexMatcher(currentLine)){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after SWITCH statement.\n");
            }
        }
    }
}
