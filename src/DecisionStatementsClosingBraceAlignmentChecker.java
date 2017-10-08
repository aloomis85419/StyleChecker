/**
 * Created by aloom on 10/8/2017.
 */
public class DecisionStatementsClosingBraceAlignmentChecker extends StyleChecker {

    public void closingBraceAlignmentCheck(String currentLine, int lineIndex,int lineNum) {
        //Recycle alignment methods from these classes
        MethodClosingBraceAlignedLeftChecker methodClosingBraceAlignedLeftChecker = new MethodClosingBraceAlignedLeftChecker();
        LoopsOpeningBraceAlignmentChecker loopsOpeningBraceAlignmentChecker = new LoopsOpeningBraceAlignmentChecker();
        int alignPos;
        String decType = "";
        int closingBracePos;
        int closingBraceLineNum;

        if (currentLine.contains("if(")) {
            decType = "if";
            alignPos = loopsOpeningBraceAlignmentChecker.getPositionOfLoopKeyword(currentLine,decType);
            closingBraceLineNum = methodClosingBraceAlignedLeftChecker.getClosingBraceIndex(lineIndex);
            closingBracePos = methodClosingBraceAlignedLeftChecker.closingBracePositionWithinLine(closingBraceLineNum);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + closingBraceLineNum + ":", " Closing brace alignment for " + decType + " statement is incorrect.\n");
            }
        }
        if (currentLine.contains("if else(")) {
            decType = "if else";
            alignPos = loopsOpeningBraceAlignmentChecker.getPositionOfLoopKeyword(currentLine,decType);
            closingBraceLineNum = methodClosingBraceAlignedLeftChecker.getClosingBraceIndex(lineIndex);
            closingBracePos = methodClosingBraceAlignedLeftChecker.closingBracePositionWithinLine(closingBraceLineNum);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + closingBraceLineNum + ":", " Closing brace alignment for " + decType + " statement is incorrect.\n");
            }
        }
        if (currentLine.contains("else(")) {
            decType = "else";
            alignPos = loopsOpeningBraceAlignmentChecker.getPositionOfLoopKeyword(currentLine,decType);
            closingBraceLineNum = methodClosingBraceAlignedLeftChecker.getClosingBraceIndex(lineIndex);
            closingBracePos = methodClosingBraceAlignedLeftChecker.closingBracePositionWithinLine(closingBraceLineNum);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + closingBraceLineNum + ":", " Closing brace alignment for " + decType + " statement is incorrect.\n");
            }
        }
        if (currentLine.contains("switch(")) {
            decType = "switch";
            alignPos = loopsOpeningBraceAlignmentChecker.getPositionOfLoopKeyword(currentLine,decType);
            closingBraceLineNum = methodClosingBraceAlignedLeftChecker.getClosingBraceIndex(lineIndex);
            closingBracePos = methodClosingBraceAlignedLeftChecker.closingBracePositionWithinLine(closingBraceLineNum);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + closingBraceLineNum + ":", " Closing brace alignment for " + decType + " statement is incorrect.\n");
            }
        }
    }
}
