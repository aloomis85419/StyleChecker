import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by aloom on 10/6/2017.
 */
public class LoopsHaveCurlysChecker extends StyleChecker{

    /**
     * Prints various messages relating to which kind of loop structure has the no curly bracket error.
     * @param currentLine: Current line being read.
     */
    public void determineLoopErrorType(String currentLine, int lineIndex,int lineNum) {
        if(currentLine.contains("while(")){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after WHILE statement.\n");
            }
        }
        if(currentLine.contains("for(")){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after WHILE statement.\n");
            }
        }
        if(currentLine.contains("do(")){
            if (!currentLine.contains("{")&& !progLines.get(lineIndex+1).contains("{") ){
                errorTrace("Line " + (lineNum) + ": ", "Missing curly bracket after WHILE statement.\n");
            }
        }
    }
}