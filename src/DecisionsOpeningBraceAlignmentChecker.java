/**
 * Created by aloom on 10/7/2017.
 */
public class DecisionsOpeningBraceAlignmentChecker extends StyleChecker {

    public void checkLoopBraceAlignment(String progLine,int lineIndex, int lineNum){
        int alignPos;
        int bracePos;
        String decType = "";
        lineNum+=1;
        if(progLine.isEmpty()){
            return;
        }
        if(progLine.contains("if(")){
            decType = "if";
            alignPos = getPositionOfDecisionStatement(progLine, decType);
            bracePos = getOpeningBraceAlignmentPosition(lineIndex);
            if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+decType+" statement is incorrect.\n");
            }
        }
        if(progLine.contains("else if(")){
            decType = "else if";
            alignPos = getPositionOfDecisionStatement(progLine, decType);
            bracePos = getOpeningBraceAlignmentPosition(lineIndex);
            if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+decType+" statement is incorrect.\n");
            }
        }
        if(progLine.contains("else")){
            decType = "else";
            alignPos = getPositionOfDecisionStatement(progLine,decType);
            bracePos = getOpeningBraceAlignmentPosition(lineIndex);
            if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+decType+" statement is incorrect.\n");
            }
        }
        if(progLine.contains("switch(")){
            decType = "switch";
            alignPos = getPositionOfDecisionStatement(progLine,decType);
            bracePos = getOpeningBraceAlignmentPosition(lineIndex);
            if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+decType+" statement is incorrect.\n");
            }
        }
    }

    public int getPositionOfDecisionStatement(String progLine, String decType){
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
