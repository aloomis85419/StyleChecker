/**
 * Checks for at least one clank line after class declaration.
 * @author : Aaron Loomis
 */
public class BlankLineAfterClassDeclarationChecker extends StyleChecker{
    /**
     * This method wil check for a blank line two lines down from the method declaration.
     * It will store an error message for printing if this line is not empty.
     * It assumes that the opening curly brace is immediately after the class declaration and skips it.
     * @param currentLine: Line being read
     */
    public void checkForBlankLineAfterClassHeader(String currentLine){
        if(currentLine.contains("class")) {
            int indexOfClassDeclaration = progLines.indexOf(currentLine);
            String temp = progLines.get(indexOfClassDeclaration+2);
            if (!temp.isEmpty()){
                errorTrace("Line "+(indexOfClassDeclaration+2)+": ","No blank line between class name and first method.\n");
            }
        }
    }
}
