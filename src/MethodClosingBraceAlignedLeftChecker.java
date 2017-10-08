import java.util.Stack;

/**
 * Created by aloom on 10/7/2017.
 */
public class MethodClosingBraceAlignedLeftChecker extends StyleChecker{

    MethodIndentationChecker methodIndentationChecker = new MethodIndentationChecker();
    MethodBraceAlignedLeftChecker methodBraceAlignedLeftChecker = new MethodBraceAlignedLeftChecker();

    /**
     * Compares the alignment of the closing brace to the alignment of the first letter of method declaration.
     * I retrieves this information from the MethodClosingBraceAlignedLeftChecker class.
     * Uses MethodIndentationChecker class methodRegexMatcher method to identify method declarations.
     */
    public void closingBraceAlignmentCheck(String currentLine, int lineCount){
        int alignPos;
        int closingBracePos;
        int closingBraceLineNum;
        if(methodIndentationChecker.methodRegexMatcher(currentLine)) {
            alignPos = methodBraceAlignedLeftChecker.getAlignPosition(currentLine);
            closingBraceLineNum = getClosingBraceLineNum(lineCount);
            closingBracePos = closingBracePositionWithinLine(closingBraceLineNum);
            if(alignPos != closingBracePos){
                errorTrace("Line "+closingBraceLineNum+": ","Incorrect closing-method brace alignment.\n");
            }
        }
    }

    /**
     * This method will find the line on which the closing brace for a method is written.
     * @return: The line of the method closing brace which is always the last closing brace.
     */
    public int getClosingBraceLineNum(int lineNum){
        int braceCount = 1;
        int methodLineNum = lineNum;
        String currentLine;
        while(braceCount != 0){
            ++methodLineNum;
            currentLine = progLines.get(methodLineNum);
            if(braceCount == 0){
                break;
            }
            if(currentLine.contains("{")){
                braceCount++;
            }
            else if (currentLine.contains("}")){
                braceCount--;
            }
        }
        return methodLineNum+1;
    }

    public int closingBracePositionWithinLine(int closingBraceLineNum){
        String line = progLines.get(closingBraceLineNum-1);
        int braceIndex = line.indexOf("}");
        return braceIndex;
    }
}
