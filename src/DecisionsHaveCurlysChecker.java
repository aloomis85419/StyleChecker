/**
 * Created by aloom on 10/6/2017.
 */
public class DecisionsHaveCurlysChecker extends StyleChecker {

    /**
     * Prints various messages relating to which kind of decision structure has the no curly bracket error. This specifically checks for one line if statements.
     * Missing curly brackets in other cases are a compile time error and aren't checked for.
     * @param progLine: Current line being read.
     * @param progNextLine: Line in next sequential order to progLine(current line).
     */
    public void determineDecisionErrorMessage(String progLine, String progNextLine, int lineCount) {
        if(progLine.contains("if(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Line "+lineCount+": ","Missing curly bracket after IF statement.\n");
            }
        }
        else if(progLine.contains("switch(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Line "+lineCount+": ","Missing curly bracket after SWITCH statement.\n");
            }
        }
        else if(progLine.contains("else if(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Line "+lineCount+": ","Missing curly bracket after ELSE IF statement.\n");
            }
        }
        else if(progLine.contains("else")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Line "+lineCount+": ","Missing curly bracket after ELSE statement.\n");
            }
        }
    }
}
