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
     * @param progLine: Current line being read.
     * @param progNextLine: Line in next sequential order to progLine(current line).
     */
    public void determineLoopErrorType(String progLine, String progNextLine,int lineCount) {
        if(progLine.contains("while(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Line "+lineCount+": ","Missing curly bracket after WHILE statement.\n");
            }
        }
        if(progLine.contains("for(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Line "+lineCount+": ","Missing curly bracket after FOR statement.\n");
            }
        }
        if(progLine.contains("do(")){
            if (!progLine.contains("{") && !progNextLine.contains("{")){
                errorTrace("Line "+lineCount+": ","Missing curly bracket after DO statement.\n");
            }
        }
    }
}
