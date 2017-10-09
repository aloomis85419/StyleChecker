/**
 * Created by aloom on 10/8/2017.
 */
public class BlankLinesBetweenMethodsChecker extends StyleChecker {

    MethodClosingBraceAlignedLeftChecker methodClosingBraceAlignedLeftChecker = new MethodClosingBraceAlignedLeftChecker();//Used for getting the closing brace of a method
    MethodIndentationChecker methodIndentationChecker = new MethodIndentationChecker(); //Regex matcher method cuts down on written code. Consider making the regex matchers their own class.

    public void checkForBlankLinesBetweenMethods(String currentLine, int lineIndex, int lineNum){
        int lineOfClosingBrace;
        if(methodIndentationChecker.methodRegexMatcher(currentLine) == true){
            lineOfClosingBrace = methodClosingBraceAlignedLeftChecker.linePositionOfClosingBrace(lineIndex);
            System.out.println("Closing brace of method: "+lineOfClosingBrace);
            System.out.println("Proglines.get(lineOfClosingBrace): "+progLines.get(lineOfClosingBrace));
            System.out.println("Line after closing brace empty? "+progLines.get(lineOfClosingBrace).isEmpty());
            if(!progLines.get(lineOfClosingBrace).isEmpty()){
                errorTrace("Line "+lineOfClosingBrace+"/"+(lineOfClosingBrace+1)+": ","No blank line between methods. \n");
            }
        }
    }
}
