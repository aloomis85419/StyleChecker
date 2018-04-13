import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * StyleChecker is a program that checks for programming style errors. RunChecks is the entry point into this program.
 * /lineIndexer IS FOR INDEXING PROGLINES WHILE lineNum IS FOR PRODUCING CORRECT POSITION OF ERROR WITHIN PROGRAM.
 * @author : Aaron C. Loomis
 * @version : 1.0
 */
//
public class RunChecks {
    static ArrayList<String>filenameList = new ArrayList<>();
    static Scanner fileEntry = new Scanner(System.in);
    public static void main(String[]args) {
        String progLineCurrent;
        String outputFileName = "StyleCheckResults.txt";
        int lineIndexer = 0;
        int lineNum = 1;
        String file = "";
        //FILE INPUT. Copy and paste files into the input box. When you have completed this type "done".
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("_____STYLE CHECKER Vers1.0: ENTER A FILE PATH NAME BELOW_____");
        file = fileEntry.nextLine();

            if(!file.isEmpty()) {
                filenameList.add(file);
                System.out.println("File: " + file + "\n\n");
                System.out.println("_____STYLE CHECKER Vers1.0: ENTER A FILE PATH NAME BELOW_____\n");
                file = fileEntry.nextLine();
            }
            else{
                throw new InputMismatchException("File is empty: ");
            }
        System.out.println("Input finished...");
        System.out.println("Now processing file for style errors..");
        System.out.println("_____Files to process____");
        for (int i = 0; i < filenameList.size(); i++) {
            System.out.println(": " + filenameList.get(i));
        }
        //CHECKER OBJECT INITIALIZATIONS
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        LoopsHaveCurlysChecker loopsHaveCurlysChecker = new LoopsHaveCurlysChecker();
        DecisionsHaveCurlysChecker decisionsHaveCurlysChecker = new DecisionsHaveCurlysChecker();
        ConstantAllCapsChecker constantAllCapsChecker = new ConstantAllCapsChecker();
        ClassNamesCapitalizedChecker classNamesCapitalizedChecker = new ClassNamesCapitalizedChecker();


        MethodOpeningBraceAlignmentChecker methodOpeningBraceAlignmentChecker = new MethodOpeningBraceAlignmentChecker();
        MethodClosingBraceAlignedLeftChecker methodClosingBraceAlignedLeftChecker = new MethodClosingBraceAlignedLeftChecker();
        DecisionsOpeningBraceAlignmentChecker decisionsOpeningBraceAlignmentChecker = new DecisionsOpeningBraceAlignmentChecker();
        LoopsOpeningBraceAlignmentChecker loopsOpeningBraceAlignmentChecker = new LoopsOpeningBraceAlignmentChecker();
        LoopsClosingBraceAlignmentChecker loopsClosingBraceAlignmentChecker = new LoopsClosingBraceAlignmentChecker();
        DecisionStatementsClosingBraceAlignmentChecker decisionStatementsClosingBraceAlignmentChecker = new DecisionStatementsClosingBraceAlignmentChecker();
        SpacesAroundBinaryMathOperatorsChecker spacesAroundBinaryMathOperatorsChecker = new SpacesAroundBinaryMathOperatorsChecker();
        BlankLinesBetweenMethodsChecker blankLinesBetweenMethodsChecker = new BlankLinesBetweenMethodsChecker();
        BlankLineAfterClassDeclarationChecker blankLineAfterClassDeclarationChecker = new BlankLineAfterClassDeclarationChecker();
        MethodNameLowerCaseChecker methodNameLowerCaseChecker = new MethodNameLowerCaseChecker();
        SpaceBetweenMethodAndParametersChecker spaceBetweenMethodAndParametersChecker = new SpaceBetweenMethodAndParametersChecker();
        NoBlankLinesAfterLoopsChecker noBlankLinesAfterLoopsChecker = new NoBlankLinesAfterLoopsChecker();
        NoBlankLinesAfterDecisionStatementsChecker noBlankLinesAfterDecisionStatementsChecker = new NoBlankLinesAfterDecisionStatementsChecker();
        ClassOpeningBraceAlignmentChecker classOpeningBraceAlignmentChecker = new ClassOpeningBraceAlignmentChecker();
        //ClassClosingBraceAlignmentChecker classClosingBraceAlignmentChecker = new ClassClosingBraceAlignmentChecker();
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        StyleChecker styleChecker;
        for (int j = 0; j < filenameList.size(); j++) {
            try {
                styleChecker = new StyleChecker(filenameList.get(j), new FileReader(filenameList.get(j)));
                styleChecker.fillListWithProgLines();
                for (int i = 0; i < styleChecker.progLines.size(); i++) {
                    progLineCurrent = styleChecker.progLines.get(i);
                    lineIndexer = i;
                    loopsHaveCurlysChecker.determineLoopErrorType(progLineCurrent,lineIndexer,lineNum);
                    decisionsHaveCurlysChecker.determineDecisionErrorMessage(progLineCurrent,lineIndexer,lineNum);
                    methodNameLowerCaseChecker.checkMethodNamesLowercase(progLineCurrent, lineIndexer, lineNum);
                    constantAllCapsChecker.constantsAllCaps(progLineCurrent, lineNum);
                    classNamesCapitalizedChecker.classNameCapitalized(progLineCurrent, lineNum);
                    spacesAroundBinaryMathOperatorsChecker.spacedBinaryOperators(progLineCurrent,lineIndexer,lineNum);
					methodOpeningBraceAlignmentChecker.methodBracesAlignedLeft(progLineCurrent,lineIndexer,lineNum);
                    methodClosingBraceAlignedLeftChecker.closingBraceAlignmentCheck(progLineCurrent,lineIndexer,lineNum);
                    decisionsOpeningBraceAlignmentChecker.checkLoopBraceAlignment(progLineCurrent,lineIndexer,lineNum);
                    loopsOpeningBraceAlignmentChecker.checkLoopBraceAlignment(progLineCurrent,lineIndexer,lineNum);
                    loopsClosingBraceAlignmentChecker.closingBraceAlignmentCheck(progLineCurrent, lineIndexer,lineNum);
                    decisionStatementsClosingBraceAlignmentChecker.closingBraceAlignmentCheck(progLineCurrent, lineIndexer, lineNum);
                    classOpeningBraceAlignmentChecker.classOpeningBracesAlignedLeft(progLineCurrent,lineIndexer,lineNum);
                    //classClosingBraceAlignmentChecker.classClosingBraceAlignmentCheck(progLineCurrent);
                    blankLinesBetweenMethodsChecker.checkForBlankLinesBetweenMethods(progLineCurrent,lineIndexer,lineNum);
                    blankLineAfterClassDeclarationChecker.checkForBlankLineAfterClassHeader(progLineCurrent);
                    spaceBetweenMethodAndParametersChecker.checkForSpaceBetweenMethodAndParameters(progLineCurrent, lineNum);
                    noBlankLinesAfterLoopsChecker.checkForBlanksAfterLoops(progLineCurrent,lineIndexer);
                    noBlankLinesAfterDecisionStatementsChecker.checkForBlanksAfterLoops(progLineCurrent, lineIndexer);
                    lineNum += 1;
                }
                styleChecker.outputFileReport(outputFileName);
            } catch (FileNotFoundException f) {
                System.out.println("File not found.");
                f.printStackTrace();
            }
        }
    }
}


