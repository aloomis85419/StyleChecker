/**
 * Checks that the characters of a method name are all lower case.
 * @author : Aaron Loomis
 */
public class MethodNameLowerCaseChecker extends StyleChecker {

    CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher();
    String[]returnTypeTokens = {"void","int","Integer","double","Double","float","Float","long","Long","short","Short","boolean","Boolean","char","Character","byte","Byte"};


    /*
           Checks if all method names consist of only lowercase letters
     */
    public void checkMethodNamesLowercase(String currentLine, int lineIndex, int lineNum){
        String methodName;
        /*method call and class accessors checks to be added in the future.
        if(codeRegexMatcher.methodCallRegexMatcher(currentLine)){
            if (codeRegexMatcher.methodLowerCaseRegexMatcher(currentLine)==false)errorTrace("Line "+lineNum+": ","Method call does not consist of exclusively lowercase letters.");
        }
        */
        if(codeRegexMatcher.methodRegexMatcher(currentLine) == true){

            if(codeRegexMatcher.methodLowerCaseRegexMatcher(currentLine) == false)errorTrace("Line "+lineNum+": ","Method name does not consist of exclusively lowercase letters.");
        }
    }

    /*
        Scans a line and finds where the method name is located. Once found it builds the method name character by character and then returns it.
     */
    public String extractMethodName(String progLine){
        int returnTypeStartIndex = 0;
        Character currentChar;
        int returnTypeEndIndex = 0;
        int methodNameStartIndex = 0;
        String sB = "";
            for (int j = 0; j < progLine.length(); j++){
                if(progLine.indexOf(returnTypeTokens[j])==0){
                    continue;
                }
                else if(progLine.indexOf(returnTypeTokens[j]) > 0){//contains one of the tokens and we need its index
                   returnTypeStartIndex = progLine.indexOf(returnTypeTokens[j]);
                   break;
                }
            }
            for (int i = returnTypeStartIndex; i < progLine.length(); i++){
                currentChar = progLine.charAt(i);
                if (!currentChar.equals(' ')){
                    continue;
                }
                else{
                    returnTypeEndIndex = i;
                    break;
                }
            }

            for (int k = returnTypeEndIndex; k < progLine.length(); k++){
                currentChar = progLine.charAt(k);
                if (currentChar == ' '){
                    continue;
                }
                else{
                    methodNameStartIndex = k;
                    break;
                }
            }

            for (int l = methodNameStartIndex; l < progLine.length(); l++){
                currentChar = progLine.charAt(l);
                if(currentChar.equals(' ') || currentChar.equals('(')){
                    break;
                }
                else{
                    sB+=currentChar;
                }
            }
            return sB;
    }

    /*
        True if the method name is all lower case (no error stored). False if there is a single uppercase letter.
     */
    private boolean isAllLowerCase(String methodName){
        Character c;
       for (int i = 0; i < methodName.length(); i++){
           c = methodName.charAt(i);
           if (!Character.isLowerCase(c)){
               return false;
           }
       }
       return true;
    }
}
