import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * StyleChecker is a program that checks for programming style errors. RunChecks is the entry point into this program.
 * @author : Aaron C. Loomis
 * @version : 1.0
 */
public class RunChecks {

    public static void main(String[]args) {
        //lineIndexer IS FOR INDEXING PROGLINES WHILE lineNum IS FOR PRODUCING CORRECT POSITION OF ERROR WITHIN PROGRAM.
        int lineIndexer = 0;
        int lineNum = 1;
        String progLineCurrent;
        String file1 = "C:\\Users\\aloom\\IdeaProjects\\StyleChecker\\src\\aaron_indent.txt";
        String outputFileName = "StyleCheckResults.txt";
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        StyleChecker styleChecker;
        MethodIndentationChecker methodIndentationChecker = new MethodIndentationChecker();
        LoopsHaveCurlysChecker loopsHaveCurlysChecker = new LoopsHaveCurlysChecker();
        DecisionsHaveCurlysChecker decisionsHaveCurlysChecker = new DecisionsHaveCurlysChecker();
        ConstantAllCapsChecker constantAllCapsChecker = new ConstantAllCapsChecker();
        ClassNamesCapitalizedChecker classNamesCapitalizedChecker = new ClassNamesCapitalizedChecker();
        SpacesAroundBinaryMathOperatorsChecker spacesAroundBinaryMathOperatorsChecker = new SpacesAroundBinaryMathOperatorsChecker();
        MethodOpeningBraceAlignmentChecker methodOpeningBraceAlignmentChecker = new MethodOpeningBraceAlignmentChecker();
        MethodClosingBraceAlignedLeftChecker methodClosingBraceAlignedLeftChecker = new MethodClosingBraceAlignedLeftChecker();
        DecisionsOpeningBraceAlignmentChecker decisionsOpeningBraceAlignmentChecker = new DecisionsOpeningBraceAlignmentChecker();
        LoopsOpeningBraceAlignmentChecker loopsOpeningBraceAlignmentChecker = new LoopsOpeningBraceAlignmentChecker();
        LoopsClosingBraceAlignmentChecker loopsClosingBraceAlignmentChecker = new LoopsClosingBraceAlignmentChecker();
        DecisionStatementsClosingBraceAlignmentChecker decisionStatementsClosingBraceAlignmentChecker = new DecisionStatementsClosingBraceAlignmentChecker();
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            styleChecker = new StyleChecker(file1, new FileReader(file1));
            styleChecker.fillListWithProgLines();
            for(int i = 0; i < styleChecker.progLines.size(); i++){
                progLineCurrent = styleChecker.progLines.get(i);
                lineIndexer = i;
                //methodIndentationChecker.methodsIndented(progLineCurrent,lineNum);WORKS WITH NEW VERSION
                //loopsHaveCurlysChecker.determineLoopErrorType(progLineCurrent,lineIndexer,lineNum);WORKS WITH NEW VERSION
                //decisionsHaveCurlysChecker.determineDecisionErrorMessage(progLineCurrent,lineIndexer,lineNum);WORKS WITH NEW VERSION
                //constantAllCapsChecker.constantsAllCaps(progLineCurrent, lineNum);WORKS WITH NEW VERSION
                //classNamesCapitalizedChecker.classNameCapitalized(progLineCurrent, lineNum);WORKS WITH NEW VERSION
                //spacesAroundBinaryMathOperatorsChecker.spacedBinaryOperators(progLineCurrent,lineIndexer,lineNum);WORKS WITH NEW VERSION
                //methodOpeningBraceAlignmentChecker.methodBracesAlignedLeft(progLineCurrent,lineIndexer,lineNum);WORKS WITH NEW VERSION
                //methodClosingBraceAlignedLeftChecker.closingBraceAlignmentCheck(progLineCurrent,lineIndexer,lineNum);WORKS WITH NEW VERSION
                //decisionsOpeningBraceAlignmentChecker.checkLoopBraceAlignment(progLineCurrent,lineIndexer,lineNum);WORKS WITH NEW VERSION
                //loopsOpeningBraceAlignmentChecker.checkLoopBraceAlignment(progLineCurrent,lineIndexer,lineNum);WORKS WITH NEW VERSION
                //loopsClosingBraceAlignmentChecker.closingBraceAlignmentCheck(progLineCurrent, lineIndexer,lineNum);WORKS WITH NEW VERSION
                //decisionStatementsClosingBraceAlignmentChecker.closingBraceAlignmentCheck(progLineCurrent, lineIndexer, lineNum);WORKS WITH NEW VERSION
                lineNum +=1;
            }
            styleChecker.outputFileReport(outputFileName);
        } catch (FileNotFoundException f) {
            System.out.println("File not found.");
            f.printStackTrace();
        }
    }
}
