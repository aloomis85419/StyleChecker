/**
 * Created by aloom on 10/6/2017.
 */
public class ClassNamesCapitalizedChecker extends StyleChecker {

    /**
     * Looks to see if the first letter of a class name is capitalized.
     * @return: False if no style errors of this type were found.
     */
    public void classNameCapitalized(String progLine, int lineCount){
        Character capCheck;
        String classId = "class";
        if(progLine.contains(classId)){
            String className;
            int decStartPOS = progLine.indexOf(classId);
            int decEndPOS = (decStartPOS) + classId.length();
            className = progLine.substring(decEndPOS, progLine.length());
            className = extractClassName(className);
            capCheck = className.charAt(0);
            if(!capCheck.toString().equals(capCheck.toString().toUpperCase()) ){
                errorTrace("Line "+lineCount+": ","Class name is not capitalized.\n");
            }
        }
    }

    /**
     * Extracts the class name from a substring of the line containing the class name.
     * @param name; A section of a program line containing the class name to be extracted.
     * @return; Returns the class name only.
     */
    public String extractClassName(String name){
        String extracted = "";
        for (int i = 0; i < name.length(); i++){

            if(name.charAt(i) == ' '){
                continue;
            }
            else{
                extracted += name.charAt(i);
            }
        }
        return extracted;
    }
}
