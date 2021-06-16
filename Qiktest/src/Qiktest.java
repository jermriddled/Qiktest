import java.io.IOException;

// Performs calculations on accuracy and performance indices to determine aggregate improvement or
// worsening in scores.  Scans entire qiktest folder, and for each patient that has more than one
// qiktest on file, scans the first page of each test and stores performance and accuracy
// indices, omission and commission errors, outlier responses, speed of response, variability,
// patient name, and test date into a dataset. Then, each patient's best subsequent test is
// compared against their first test, with percentage change in each index stored as a positive
// or negative decimal representing a percentage; these are added to a total sum, which is divided
// by the number of patients to find overall change

// FLAG AS SIGNIFICANT IF:

// CATEGORY A: if a baseline standard score is 115 or higher and decreases by 15 or more

// CATEGORY B: if a baseline standard score is 90 or higher and increases or decreases by 12
// or more

// CATEGORY C: if a baseline standard score is between 78 and 90 and increases or
// decreases by 8 or more

// CATEGORY D: if a baseline standard score increases from under 90 to 90 or above AND by 8
// or more points

// CATEGORY E: if a baseline standard score increases from 70 or below to 78 or above

public class Qiktest {

    public static void main(String[] args) throws IOException {

        DataUtil.main(null);
        var evaluateUtil = new EvaluationUtil(new SignificantSubsequentOmissionScore(),
                new SignificantSubsequentCommissionScore(), new SignificantSubsequentVariabilityScore());
        evaluateUtil.evaluateAll();
        DataUtil.printResults();

    }
}







