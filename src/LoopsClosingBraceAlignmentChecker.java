/**
 * Created by aloom on 10/8/2017.
 */

public class LoopsClosingBraceAlignmentChecker extends StyleChecker {

    /**
     * Finds the closing brace to various loop types and checks if they are aligned directly under the first letter of their declaration.
     * @param currentLine: Line being read.
     * @param lineIndex: Index used for seaching progLines list if needed.
     * @param lineNum: one more than lineIndex. Used to display line numbers indexed starting at 1.
     */
    public void closingBraceAlignmentCheck(String currentLine, int lineIndex,int lineNum) {
        //Recycle alignment methods from these classes
        MethodClosingBraceAlignedLeftChecker methodClosingBraceAlignedLeftChecker = new MethodClosingBraceAlignedLeftChecker();
        LoopsOpeningBraceAlignmentChecker loopsOpeningBraceAlignmentChecker = new LoopsOpeningBraceAlignmentChecker();
        int alignPos;
        String decType = "";
        int closingBracePos;
        int closingBraceLineNum;

        if (currentLine.contains("do(")) {
            decType = "do";
            alignPos = loopsOpeningBraceAlignmentChecker.getPositionOfLoopKeyword(currentLine,decType);
            closingBraceLineNum = methodClosingBraceAlignedLeftChecker.getClosingBraceIndex(lineIndex);
            closingBracePos = methodClosingBraceAlignedLeftChecker.closingBracePositionWithinLine(closingBraceLineNum);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + closingBraceLineNum + ":", " Closing brace alignment for " + decType + " statement is incorrect.\n");
            }
        }
        if (currentLine.contains("while(")) {
            decType = "while";
            alignPos = loopsOpeningBraceAlignmentChecker.getPositionOfLoopKeyword(currentLine,decType);
            closingBraceLineNum = methodClosingBraceAlignedLeftChecker.getClosingBraceIndex(lineIndex);
            closingBracePos = methodClosingBraceAlignedLeftChecker.closingBracePositionWithinLine(closingBraceLineNum);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + closingBraceLineNum + ":", " Closing brace alignment for " + decType + " statement is incorrect.\n");
            }
        }
        if (currentLine.contains("for(")) {
            decType = "for";
            alignPos = loopsOpeningBraceAlignmentChecker.getPositionOfLoopKeyword(currentLine,decType);
            closingBraceLineNum = methodClosingBraceAlignedLeftChecker.getClosingBraceIndex(lineIndex);
            closingBracePos = methodClosingBraceAlignedLeftChecker.closingBracePositionWithinLine(closingBraceLineNum);
            if (alignPos != closingBracePos) {
                errorTrace("Line " + closingBraceLineNum + ":", " Closing brace alignment for " + decType + " statement is incorrect.\n");
            }
        }
    }
}