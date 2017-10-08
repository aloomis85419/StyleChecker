/**
 * Created by aloom on 10/8/2017.
 */
public class LoopsOpeningBraceAlignmentChecker extends StyleChecker {
    public void checkLoopBraceAlignment(String progLine, int lineIndex, int lineNum){
        int alignPos;
        int bracePos;
        String decType = "";
        if(progLine.isEmpty()){
            return;
        }
        if(progLine.contains("do(")){
            decType = "do";
            alignPos = getPositionOfLoopKeyword(progLine, decType);
            bracePos = getOpeningBraceAlignmentPosition(lineIndex);
            if(alignPos != bracePos){
                errorTrace("Line "+(lineNum+1)+":"," Opening brace alignment for "+decType+" statement is incorrect.\n");
            }
        }
        if(progLine.contains("while(")){
            decType = "while";
            alignPos = getPositionOfLoopKeyword(progLine, decType);
            bracePos = getOpeningBraceAlignmentPosition(lineIndex);
            if(alignPos != bracePos){
                errorTrace("Line "+(lineNum+1)+":"," Opening brace alignment for "+decType+" statement is incorrect.\n");
            }
        }
        if(progLine.contains("for(")){
            decType = "for";
            alignPos = getPositionOfLoopKeyword(progLine,decType);
            bracePos = getOpeningBraceAlignmentPosition(lineIndex);
            if(alignPos != bracePos){
                errorTrace("Line "+(lineNum+1)+":"," Opening brace alignment for "+decType+" statement is incorrect.\n");
            }
        }
    }

    public int getPositionOfLoopKeyword(String progLine, String decType){
        int decPos = progLine.indexOf(decType);
        return decPos;
    }

    public int getOpeningBraceLineNum(int lineCount){
        int lineNum = lineCount;
        String progLine;
        while (!progLines.get(lineNum).contains("{")){
            lineNum++;
            progLine = progLines.get(lineNum);
        }
        return lineNum;
    }

    public int getOpeningBraceAlignmentPosition(int lineCount){
        int posOfBrace = 0;
        while(progLines.get(getOpeningBraceLineNum(lineCount)).charAt(posOfBrace) != '{') {
            posOfBrace++;
        }
        return  posOfBrace;
    }
}
