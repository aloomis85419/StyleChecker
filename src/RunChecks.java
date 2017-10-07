import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * StyleChecker is a program that checks for programming style errors. RunChecks is the entry point into this program.
 * @author : Aaron C. Loomis
 * @version : 1.0
 */
public class RunChecks {

    public static void main(String[]args) {

        StyleChecker styleChecker;
        MethodIndentationChecker methodIndentationChecker = new MethodIndentationChecker();
        LoopsHaveCurlysChecker loopsHaveCurlysChecker = new LoopsHaveCurlysChecker();
        DecisionsHaveCurlysChecker decisionsHaveCurlysChecker = new DecisionsHaveCurlysChecker();
        ConstantAllCapsChecker constantAllCapsChecker = new ConstantAllCapsChecker();
        ClassNameCapitalizedCheck classNameCapitalizedCheck = new ClassNameCapitalizedCheck();
        int lineCount = 0;
        String progLineCurrent;
        String progLinePrevious;
        String file1 = "C:\\Users\\aloom\\IdeaProjects\\StyleChecker\\src\\aaron_indent.txt";
        String outputFileName = "StyleCheckResults.txt";

        try {
            styleChecker = new StyleChecker(file1, new FileReader(file1), 0);
            styleChecker.fillListWithProgLines();
            for(int i = 1; i < styleChecker.progLines.size(); i++){
                lineCount += 1;
                progLinePrevious = styleChecker.progLines.get(i - 1);
                progLineCurrent = styleChecker.progLines.get(i);
                methodIndentationChecker.methodsIndented(progLinePrevious,lineCount);
                loopsHaveCurlysChecker.determineLoopErrorType(progLinePrevious, progLineCurrent, lineCount);
                decisionsHaveCurlysChecker.determineDecisionErrorMessage(progLinePrevious, progLineCurrent, lineCount);
                constantAllCapsChecker.constantsAllCaps(progLinePrevious, lineCount);
                classNameCapitalizedCheck.classNameCapitalized(progLinePrevious, lineCount);
            }
            styleChecker.outputFileReport(outputFileName);
        } catch (FileNotFoundException f) {
            System.out.println("File not found.");
            f.printStackTrace();
        } catch (IOException fileReadError) {
            System.out.println("Error reading file.");
            fileReadError.printStackTrace();
        }
    }
}
