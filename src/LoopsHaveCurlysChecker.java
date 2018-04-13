import com.sun.org.apache.bcel.internal.classfile.Code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : Aaron Loomis
 */
public class LoopsHaveCurlysChecker extends StyleChecker{

    CodeRegexMatcher codeRegexMatcher = new CodeRegexMatcher();
    /**
     * Prints various messages relating to which kind of loop structure has the no curly bracket error.
     * @param currentLine: Current line being read.
     */
    public void determineLoopErrorType(String currentLine, int lineIndex,int lineNum) {
        if(codeRegexMatcher.whileRegexMatcher(currentLine)){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after WHILE statement.\n");
            }
        }
        if(codeRegexMatcher.forRegexMatcher(currentLine)){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after FOR statement.\n");
            }
        }
        if(codeRegexMatcher.doRegexMatcher(currentLine)){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after DO statement.\n");
            }
        }
    }
}