/**
 * Created by aloom on 10/7/2017.
 */
public class MethodClosingBraceAlignedLeftChecker extends StyleChecker{

    MethodIndentationChecker methodIndentationChecker = new MethodIndentationChecker();
    MethodOpeningBraceAlignmentChecker methodOpeningBraceAlignmentChecker = new MethodOpeningBraceAlignmentChecker();

    /**
     * Compares the alignment of the closing brace to the alignment of the first letter of method declaration.
     * I retrieves this information from the MethodClosingBraceAlignedLeftChecker class.
     * Uses MethodIndentationChecker class methodRegexMatcher method to identify method declarations.
     */
    public void closingBraceAlignmentCheck(String currentLine, int lineIndex,int lineNum){
        int alignPos;
        int closingBraceIndexPos;
        if(methodIndentationChecker.methodRegexMatcher(currentLine)){
            alignPos = methodOpeningBraceAlignmentChecker.getAlignPosition(currentLine);
            closingBraceIndexPos = closingBracePositionWithinLine(getClosingBraceIndex(lineIndex));
            if(alignPos != closingBraceIndexPos){
                errorTrace("Line "+linePositionOfClosingBrace(lineIndex)+": ","Incorrect closing-method brace alignment.\n");
            }
        }
    }

    /**
     * This method will find the line on which the closing brace for a method is written.
     * @return: The line of the method closing brace which is always the last closing brace.
     */
    public int getClosingBraceIndex(int lineIndex){
        int tempLI = lineIndex+1;
        int count = tempLI;
        String currentLine = progLines.get(lineIndex);
        int braceCount = 1;
        while(braceCount != 0 && tempLI < progLines.size() - 1){
            count++;
            if(braceCount==0){
                break;
            }
            if(currentLine.contains("{")){
                braceCount++;
            }
            else if (currentLine.contains("}")){
                braceCount--;
            }
            currentLine = progLines.get(tempLI+=1);
        }
        return count;
    }

    public int linePositionOfClosingBrace(int lineIndexer){
        return getClosingBraceIndex(lineIndexer);
    }

    public int closingBracePositionWithinLine(int lineIndexer){
        String line = progLines.get(lineIndexer-1);
        int braceIndex = line.indexOf("}");
        return braceIndex;
    }
}
