import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Checks the alignment of the opening brace of a loop statement.
 * @author : Aaron Loomis
 */
public class LoopsOpeningBraceAlignmentChecker extends StyleChecker {

    CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher();

    /*
        Checks to see if the opening brace of a loop statement is in alignment with the first character of the loop statement.
     */
    public void checkLoopBraceAlignment(String progLine, int lineIndex, int lineNum){
        int alignPos = 0;
        int bracePos = 0;
        String decType = "";
        if(progLine.isEmpty()){
            return;
        }
        if(codeRegexMatcher.doRegexMatcher(progLine)){
            decType = "DO";
            if(progLines.get(lineIndex).contains("{")){
                errorTrace("Line "+lineNum+": ","Opening brace on the same line as "+decType+" header.");
                return;
            }
            if (progLines.get(lineIndex+1).contains("{")) {
                alignPos = getPositionOfKeyword(progLines.get(lineIndex), decType);
                bracePos = getOpeningBraceAlignmentPosition(progLines.get(lineIndex+1));
            }
            if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+decType+" statement is incorrect.\n");
                return;
            }
        }
        if(codeRegexMatcher.whileRegexMatcher(progLine)){
            decType = "WHILE";
            if(progLines.get(lineIndex).contains("{")){
                errorTrace("Line "+lineNum+": ","Opening brace on the same line as "+decType+" header.");
                return;
            }
            if (progLines.get(lineIndex+1).contains("{")) {
                alignPos = getPositionOfKeyword(progLines.get(lineIndex), decType);
                bracePos = getOpeningBraceAlignmentPosition(progLines.get(lineIndex+1));

            }if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+decType+" statement is incorrect.\n");
                return;
            }

        }
        if(codeRegexMatcher.forRegexMatcher(progLine)){
            decType = "FOR";
            if(progLines.get(lineIndex).contains("{")){
                errorTrace("Line "+lineNum+": ","Opening brace on the same line as "+decType+" header.");
                return;
            }
            if (progLines.get(lineIndex+1).contains("{")) {
                alignPos = getPositionOfKeyword(progLines.get(lineIndex), decType);
                bracePos = getOpeningBraceAlignmentPosition(progLines.get(lineIndex+1));
            }
            if(alignPos != bracePos){
                errorTrace("Line "+lineNum+":"," Opening brace alignment for "+decType+" statement is incorrect.\n");
                return;
            }
        }
    }

    /*
        Returns the idnex of the first character of the keyword.
     */
    public int getPositionOfKeyword(String currentLine, String decType){
        int index = 0;
        while(currentLine.charAt(index)== ' '){
            index++;
        }
        return index;
    }

    public int getOpeningBraceAlignmentPosition(String progLine){
        Character currentChar;
        for (int i = 0; i < progLine.length(); i++){
            currentChar = progLine.charAt(i);
            if (currentChar == '{'){
                return i;
            }
        }
      return 0;
    }
}
