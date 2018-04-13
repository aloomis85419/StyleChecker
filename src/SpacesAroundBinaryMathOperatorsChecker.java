/**
 * Checks that binary operators have exactly one space around them.
 * @author : Aaron Loomis
 */
public class SpacesAroundBinaryMathOperatorsChecker extends StyleChecker {

    int lineCount;
    /*
        Stores an error message if
     */
    public void spacedBinaryOperators(String progLine,int lineIndex, int lineNum){
        this.lineCount = lineNum;
        if (progLine.contains("/*") || progLine.contains("/**") ||progLine.contains("*/") ||progLine.contains("//")){
            progLine = progLines.get(lineIndex+1);
        }
        searchForBinaryMathOperators(progLine);
    }

    /*
        Searches for binary operators in a line of code.
     */
    public void searchForBinaryMathOperators(String progLine)
    {
        for (int i = 2; i < progLine.length(); i++)
        {
            Character previousChar = progLine.charAt(i - 2);
            Character currentChar = progLine.charAt(i - 1);
            Character nextChar = progLine.charAt(i);
            //These checks do not take into account multiple character operators like != += ect. I would like to add this later.
            if(currentChar.toString().equals("+")){
                spacesSurroundCharacter(previousChar.toString(),currentChar.toString(),nextChar.toString());
            }
            if(currentChar.toString().equals("-")){
                spacesSurroundCharacter(previousChar.toString(),currentChar.toString(),nextChar.toString());
            }
            if(currentChar.toString().equals("*")){
                spacesSurroundCharacter(previousChar.toString(),currentChar.toString(),nextChar.toString());
            }
            if(currentChar.toString().equals("/")){
                spacesSurroundCharacter(previousChar.toString(),currentChar.toString(),nextChar.toString());
            }
            if(currentChar.toString().equals("%")){
                spacesSurroundCharacter(previousChar.toString(),"MODULUS",nextChar.toString());
            }
            if(currentChar.toString().equals("<=")){
                spacesSurroundCharacter(previousChar.toString(),"MODULUS",nextChar.toString());
            }
            if(currentChar.toString().equals(">=")){
                spacesSurroundCharacter(previousChar.toString(),"MODULUS",nextChar.toString());
            }
        }
    }
    public void spacesSurroundCharacter(String before, String current, String after){

        if (!before.equals(" ") || !after.equals(" ")){
            errorTrace("Line "+this.lineCount+": ","Missing spaces around " +current+ " operator.\n");
        }
    }
}
