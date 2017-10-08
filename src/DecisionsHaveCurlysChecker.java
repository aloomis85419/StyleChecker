/**
 * Created by aloom on 10/6/2017.
 */
public class DecisionsHaveCurlysChecker extends StyleChecker {

    /**
     * Prints various messages relating to which kind of decision structure has the no curly bracket error. This specifically checks for one line if statements.
     * Missing curly brackets in other cases are a compile time error and aren't checked for.
     */
    public void determineDecisionErrorMessage(String currentLine, int lineIndex,int lineNum) {
        if(currentLine.contains("if(")){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after IF statement.\n");
            }
        }
        if(currentLine.contains("if else(")){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after IF ELSE statement.\n");
            }
        }
        if(currentLine.contains("else")){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after ELSE statement.\n");
            }
        }
        if(currentLine.contains("switch(")){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after SWITCH statement.\n");
            }
        }
    }
}
