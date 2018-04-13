/**
 * Checks for spaces between a method and its parameters.
 * @author : Aaron Loomis
 */
public class SpaceBetweenMethodAndParametersChecker extends StyleChecker{
    CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher();

    /**
     * Prints a style error if there are apaces between a method and its parameters. This is a custom check I wrote.
     * @param currentLine: Line being read
     * @param lineNum: What line the error occurred on.
     */
    public void checkForSpaceBetweenMethodAndParameters(String currentLine, int lineNum){
        int parenthesisPosition;
        if(codeRegexMatcher.methodRegexMatcher(currentLine) == true){
           parenthesisPosition = findOpeningParenthesis(currentLine);
           if (checkForSpaces(currentLine, parenthesisPosition) == true){
               errorTrace("Line "+lineNum+": ","Spaces between method name and parameters");
           }
        }
    }

    /**
     * Finds the index position of the first opening parenthesis of the parameters of a method.
     * @param currentLine: Line being read.
     * @return: The index of the first opening parenthesis. Returns 0 to indicate a parenthesis was not found.
     */
    public int findOpeningParenthesis(String currentLine){
        Character c;
        for (int i = 0; i < currentLine.length(); i++){
            c = currentLine.charAt(i);
            if(c == '('){
                return currentLine.indexOf(c);
            }
        }
        return 0;
    }

    /**
     * If the char at index of opening paren of the method parameter list minus one is a space we have a style error.
     * @param currentLine;Line to be read.
     * @param parenthesisPos: Value returned for findOpeningParenthesis
     * @return: True if there is a space between a method name and a parameter list. False other wise
     */
    private boolean checkForSpaces(String currentLine,int parenthesisPos) {
            char spaceIndex = currentLine.charAt(parenthesisPos-1);
            char nextSpaceIndex = currentLine.charAt(parenthesisPos -2);
            if(spaceIndex == ' ' ){
                return true; // there are spaces
            }
        return  false;
    }
}
