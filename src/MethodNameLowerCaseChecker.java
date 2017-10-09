/**
 * Created by aloom on 10/9/2017.
 */
public class MethodNameLowerCaseChecker extends StyleChecker {

    MethodIndentationChecker methodIndentationChecker = new MethodIndentationChecker();
    //the return types array is special because a return type always comes right before a method name.
    String[]returnTypeTokens = {"void","int","Integer","double","Double","float","Float","long","Long","short","Short","boolean","Boolean","char","Character","byte","Byte"};
    public void checkMethodNamesLowercase(String currentLine, int lineIndex, int lineNum){
        if(methodIndentationChecker.methodRegexMatcher(currentLine) == true){
            String methodName;
            methodName = extractMethodName(currentLine);
            if(isAllLowerCase(methodName) == false){
                System.out.println("Is all LowerCase: "+isAllLowerCase(methodName));
                errorTrace("Line "+lineNum+": ","Method name does not consist of exclusively lowercase letters.");
            }
        }
    }

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
