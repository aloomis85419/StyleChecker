/**
 * Checks that the opening brace of a method is aligned correctly.
 * @author : Aaron Loomis
 */
public class MethodOpeningBraceAlignmentChecker extends StyleChecker{

    /**
     * This method will compare values alignPosition (the position of the first letter of a method) to matchPosition(position of opening curly brace) to see if they are equal.
     * If the values aren't equal, it will store an error message using errorTrace for printing later on in a report.
     * It is also responsible for checking the alignment of the closing brace of a method. The same action as above is performed for the closing brace.
     * @param currentLine: Line of program being analyzed.
     * @param lineNum: Passed in from RunChecks, it counts the line number for error printing.
     * @param lineIndex: Passed in from RunChecks, it allows for access of progLines;
     */
    public void methodBracesAlignedLeft(String currentLine,int lineIndex,int lineNum){
        CodeRegexMatcher methodRegexSearcher = new CodeRegexMatcher();
        int alignPosition = 0;
        if (methodRegexSearcher.methodRegexMatcher(currentLine) || methodRegexSearcher.constructorRegexMatcher(currentLine)) {
            int openingBracePos = 0;
            int indexOfNext = progLines.indexOf(currentLine)+1;
            String nextLine = progLines.get(indexOfNext);
            if(currentLine.contains("{")){
                   errorTrace("Line "+(lineNum)+": ","Incorrect method brace alignment. Opening brace is on the same line as method declaration.\n");
                }
            if(nextLine.isEmpty()){
                    errorTrace("Line " + (lineNum+1) + ": ", "Blank line after method declaration. Please fix this error before checking method brace alignment.\n");
                }
            if(nextLine.contains("{")) {
                openingBracePos = getMatchPosition(progLines.get(lineIndex + 1));
                alignPosition = getAlignPosition(currentLine);
                if (alignPosition != openingBracePos) {
                    errorTrace("Line " + (lineNum+1) + ": ", "Incorrect opening-method brace alignment.\n");
                }
            }
        }
    }

    /**
     * This method find the fist letter of a method declaration.
     * @param currentLine: Line of program to analyze.
     * @return: The position of the first letter of a method declaration.
     */
    public Integer getAlignPosition(String currentLine){
        Character currentChar;
        Integer alignPosition = 0;
        for(int i = 0; i < currentLine.length(); i++){
            currentChar = currentLine.charAt(i);
            if(currentChar == ' '){
                continue;
            }
            else if(currentChar != ' '){
                alignPosition = i;
                break;
            }
        }
       return  alignPosition;
    }

    /**
     * Finds the position of the opening curly brace.
     * @param nextLine: The line after currentLine;
     * @return: The position of the opening curly brace
     */
    public Integer getMatchPosition(String nextLine) {
        Character currentChar;
        Integer matchPosition = 0;
        for (int i = 0; i < nextLine.length(); i++) {
            currentChar = nextLine.charAt(i);
            if (currentChar == '{') {
                matchPosition = i;
                break;
            }
        }
        return matchPosition;
    }
}
