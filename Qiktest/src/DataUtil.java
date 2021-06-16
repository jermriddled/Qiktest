import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;

// Utility class for collecting, storing, and returning test data

public class DataUtil {

    public static Map<String, Integer> months;
    public static Map<String, ArrayList<PatientTestData>> data;
    public static double numberOfPatients;
    public static List<PatientStatisticalChanges> categoryA_Omissions = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryA_Commissions = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryA_Var = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryB_Omissions = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryB_Commissions = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryB_Var = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryC_Omissions = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryC_Commissions = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryC_Var = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryD_Omissions = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryD_Commissions = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryD_Var = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryE_Omissions = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryE_Commissions = new ArrayList<>();
    public static List<PatientStatisticalChanges> categoryE_Var = new ArrayList<>();
    public static List<ArrayList<String>> categoryAPatients = new ArrayList<>();
    public static List<ArrayList<String>> categoryBIncreasePatients = new ArrayList<>();
    public static List<ArrayList<String>> categoryBDecreasePatients = new ArrayList<>();
    public static List<ArrayList<String>> categoryCIncreasePatients = new ArrayList<>();
    public static List<ArrayList<String>> categoryCDecreasePatients = new ArrayList<>();
    public static List<ArrayList<String>> categoryDPatients = new ArrayList<>();
    public static List<ArrayList<String>> categoryEPatients = new ArrayList<>();

    public static void main (String[] args) throws IOException {

        addMonths();
        addDataStructures();
        collectFiles();
        numberOfPatients = data.size();
    }

    private static void addMonths() {

        months = new HashMap<>();
        months.put("Jan", 1);
        months.put("Feb", 2);
        months.put("Mar", 3);
        months.put("Apr", 4);
        months.put("May", 5);
        months.put("Jun", 6);
        months.put("Jul", 7);
        months.put("Aug", 8);
        months.put("Sep", 9);
        months.put("Oct", 10);
        months.put("Nov", 11);
        months.put("Dec", 12);

    }

    private static void addDataStructures() {

        ArrayList<String> omissions = new ArrayList<>();
        ArrayList<String> commissions = new ArrayList<>();
        ArrayList<String> var = new ArrayList<>();

        categoryAPatients.add(omissions);
        categoryAPatients.add(commissions);
        categoryAPatients.add(var);
        categoryBIncreasePatients.add(omissions);
        categoryBIncreasePatients.add(commissions);
        categoryBIncreasePatients.add(var);
        categoryBDecreasePatients.add(omissions);
        categoryBDecreasePatients.add(commissions);
        categoryBDecreasePatients.add(var);
        categoryCIncreasePatients.add(omissions);
        categoryCIncreasePatients.add(commissions);
        categoryCIncreasePatients.add(var);
        categoryCDecreasePatients.add(omissions);
        categoryCDecreasePatients.add(commissions);
        categoryCDecreasePatients.add(var);
        categoryDPatients.add(omissions);
        categoryDPatients.add(commissions);
        categoryDPatients.add(var);
        categoryEPatients.add(omissions);
        categoryEPatients.add(commissions);
        categoryEPatients.add(var);

        // "data" is the collection of all test data for all patients who have taken more than one qiktest
        data = new HashMap<>();

    }

    // Collects test files of all patients that have more than one comparable test on file
    private static void collectFiles() throws IOException {

        // Created to store arrays of scores
        ArrayList<PatientTestData> list = new ArrayList<>();

        File folder = new File(Constants.LIVE_FOLDER_PATH);
        File[] listFiles = folder.listFiles();
        String nameSpace = "@)*$)*@$@*!@%@%)*@!%*)!";
        String nameNoSpace = "@)*$)*@$@*!@%@%)*@!%*)!";
        boolean flag = false;
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {

                // Captures patient name
                String filename = listFiles[i].getName();
                MatcherUtil.getNameMatchers(filename);

                // Corner case for last file
                if (i == listFiles.length - 1) {
                    if (filename.contains(nameSpace) ||
                            filename.contains(nameNoSpace)) {
                        try {
                            PatientTestData scores = getScoresArray(listFiles, i, nameSpace);
                            if (scores == null) continue;
                            list.add(scores);
                            Collections.sort(list);
                            data.put(nameSpace, list);
                        } catch (IOException e) {
                            continue;
                        }
                    }
                    break;
                }

                // Adds this qiktest if patient has one more qiktest after this one on file
                if (MatcherUtil.NAME_MATCHER.find()) {
                    nameSpace = MatcherUtil.NAME_MATCHER.group(2) + " " + MatcherUtil.NAME_MATCHER.group(3);
                    nameNoSpace = MatcherUtil.NAME_MATCHER.group(2) + MatcherUtil.NAME_MATCHER.group(3);
                }
                if (listFiles[i + 1].getName().contains(nameSpace) ||
                        listFiles[i + 1].getName().contains(nameNoSpace)) {
                    try {
                        PatientTestData scores = getScoresArray(listFiles, i, nameSpace);
                        if (scores == null) continue;
                        list.add(scores);
                        flag = true;
                    } catch (IOException ignored) {
                    }
                }

                // Adds this qiktest if patient has multiple qiktests on file and this is
                // the last one
                else if (flag) {
                    try {
                        PatientTestData scores = getScoresArray(listFiles, i, nameSpace);
                        if (scores == null) continue;
                        list.add(scores);
                        Collections.sort(list);
                        data.put(nameSpace, list);
                        list = new ArrayList<>();
                        flag = false;
                    } catch (IOException ignored) {
                    }

                }
            }
        }
    }

    // Loads pdf, scans first page, adds scores, dates, and name to array and returns it
    private static PatientTestData getScoresArray (File[] listFiles, int i, String n) throws IOException {

        PDDocument document = PDDocument.load(listFiles[i]);
        PDFTextStripper PDFstripper = new PDFTextStripper();
        PDFstripper.setEndPage(1);
        String page = PDFstripper.getText(document);

        MatcherUtil.getDataMatchers(page);

        double performance = 0;
        double accuracy = 0;
        double omissions = 0;
        double omissionsScore = 0;
        double outliers = 0;
        double outliersScore = 0;
        double commissions = 0;
        double commissionsScore = 0;
        double time = 0;
        double timeScore = 0;
        double var = 0;
        double varScore = 0;

        LocalDate testDate = null;
        LocalDate cutoff = LocalDate.of(2015, 1, 2); // cut off for old tests
        if (MatcherUtil.PERFORMANCE_PATTERN_MATCHER.find())
            performance = Double.parseDouble(MatcherUtil.PERFORMANCE_PATTERN_MATCHER.group(1));
        if (MatcherUtil.ACCURACY_PATTERN_MATCHER.find())
            accuracy = Double.parseDouble(MatcherUtil.ACCURACY_PATTERN_MATCHER.group(1));
        if (MatcherUtil.OMISSION_ERRORS_MATCHER.find()) {
            omissions = Double.parseDouble(MatcherUtil.OMISSION_ERRORS_MATCHER.group(1));
            omissionsScore = Double.parseDouble(MatcherUtil.OMISSION_ERRORS_MATCHER.group(2));
        }
        if (MatcherUtil.OUTLIER_RESPONSES_MATCHER.find()) {
            outliers = Double.parseDouble(MatcherUtil.OUTLIER_RESPONSES_MATCHER.group(1));
            outliersScore = Double.parseDouble(MatcherUtil.OUTLIER_RESPONSES_MATCHER.group(2));
        }
        if (MatcherUtil.COMMISSION_ERRORS_MATCHER.find()) {
            commissions = Double.parseDouble(MatcherUtil.COMMISSION_ERRORS_MATCHER.group(1));
            commissionsScore = Double.parseDouble(MatcherUtil.COMMISSION_ERRORS_MATCHER.group(2));
        }
        if (MatcherUtil.RESPONSE_TIME_MATCHER.find()) {
            time = Double.parseDouble(MatcherUtil.RESPONSE_TIME_MATCHER.group(1));
            timeScore = Double.parseDouble(MatcherUtil.RESPONSE_TIME_MATCHER.group(2));
        }
        if (MatcherUtil.VARIABILITY_MATCHER.find()) {
            var = Double.parseDouble(MatcherUtil.VARIABILITY_MATCHER.group(1));
            varScore = Double.parseDouble(MatcherUtil.VARIABILITY_MATCHER.group(2));
        }
        if (MatcherUtil.DATE_MATCHER.find()) {
            testDate = LocalDate.of(Integer.parseInt(MatcherUtil.DATE_MATCHER.group(3)),
                    months.get(MatcherUtil.DATE_MATCHER.group(1)),
                    Integer.parseInt(MatcherUtil.DATE_MATCHER.group(2)));
        }
        document.close();

        // Return null if test is in old format
        if (testDate == null) return null;
        if (testDate.isBefore(cutoff)) return null;
        return new PatientTestData(n, performance, accuracy, omissions, omissionsScore, outliers,
                outliersScore, commissions, commissionsScore, time, timeScore, var, varScore, testDate);

    }

    // Prints total number of times each category in each metric is satisfied
    public static void printResults() {

        printOmissionResults();
        printCommissionResults();
        printVariabilityResults();

    }

    private static void printOmissionResults() {

        int omissionIncreasesCategoryB = 0;
        int omissionDecreasesCategoryB = 0;
        int omissionIncreasesCategoryC = 0;
        int omissionDecreasesCategoryC = 0;
        NumberFormat formatter = new DecimalFormat("#0.0");
        for (PatientStatisticalChanges entry : categoryB_Omissions) {
            if (entry.getChange() < 0) omissionDecreasesCategoryB++;
            else if (entry.getChange() > 0) omissionIncreasesCategoryB++;
        }
        for (PatientStatisticalChanges entry : categoryC_Omissions) {
            if (entry.getChange() < 0) omissionDecreasesCategoryC++;
            else if (entry.getChange() > 0) omissionIncreasesCategoryC++;
        }

        int omissionIncreasesTotal = omissionIncreasesCategoryB + omissionIncreasesCategoryC +
                categoryD_Omissions.size() + categoryE_Omissions.size();
        int omissionDecreasesTotal = categoryA_Omissions.size() + omissionDecreasesCategoryB +
                omissionDecreasesCategoryC;
        System.out.println();
        System.out.println("# of patients evaluated: " + numberOfPatients);
        System.out.println("OMISSIONS:");
        System.out.println("Scores of 115+, decreased by 15+: " + categoryA_Omissions.size());
        System.out.println("Scores 90+ increased by 12+: " + omissionIncreasesCategoryB);
        System.out.println("Scores 90+ decreased by 12+: " + omissionDecreasesCategoryB);
        System.out.println("Scores between 78 and 90 increased by 8+: " + omissionIncreasesCategoryC);
        System.out.println("Scores between 78 and 90 decreased by 8+: " + omissionDecreasesCategoryC);
        System.out.println("Scores below 90 increased to 90+ and by 8+: " + categoryD_Omissions.size());
        System.out.println("Scores below 70 increased to 78+: " + categoryE_Omissions.size());
        System.out.println("% of significant increases: " + formatter.format(
                (omissionIncreasesTotal / numberOfPatients)
                        * 100) + "%");
        System.out.println("% of significant decreases: " + formatter.format(
                (omissionDecreasesTotal / numberOfPatients)
                        * 100) + "%");
        System.out.println();

    }

    private static void printCommissionResults() {

        int commissionIncreasesCategoryB = 0;
        int commissionDecreasesCategoryB = 0;
        int commissionIncreasesCategoryC = 0;
        int commissionDecreasesCategoryC = 0;
        NumberFormat formatter = new DecimalFormat("#0.0");
        for (PatientStatisticalChanges entry : categoryB_Commissions) {
            if (entry.getChange() < 0) commissionDecreasesCategoryB++;
            else if (entry.getChange() > 0) commissionIncreasesCategoryB++;
        }
        for (PatientStatisticalChanges entry : categoryC_Commissions) {
            if (entry.getChange() < 0) commissionDecreasesCategoryC++;
            else if (entry.getChange() > 0) commissionIncreasesCategoryC++;
        }

        int commissionIncreasesTotal = commissionIncreasesCategoryB + commissionIncreasesCategoryC +
                categoryD_Commissions.size() + categoryE_Commissions.size();
        int commissionDecreasesTotal = categoryA_Commissions.size() + commissionDecreasesCategoryB +
                commissionDecreasesCategoryC;
        System.out.println("COMMISSIONS:");
        System.out.println("Scores of 115+, decreased by 15+: " + categoryA_Commissions.size());
        System.out.println("Scores 90+ increased by 12+: " + commissionIncreasesCategoryB);
        System.out.println("Scores 90+ decreased by 12+: " + commissionDecreasesCategoryB);
        System.out.println("Scores between 78 and 90 increased by 8+: " + commissionIncreasesCategoryC);
        System.out.println("Scores between 78 and 90 decreased by 8+: " + commissionDecreasesCategoryC);
        System.out.println("Scores below 90 increased to 90+ and by 8+: " + categoryD_Commissions.size());
        System.out.println("Scores below 70 increased to 78+: " + categoryE_Commissions.size());
        System.out.println("% of significant increases: " + formatter.format(
                (commissionIncreasesTotal / numberOfPatients)
                        * 100) + "%");
        System.out.println("% of significant decreases: " + formatter.format(
                (commissionDecreasesTotal / numberOfPatients)
                        * 100) + "%");
        System.out.println();

    }

    private static void printVariabilityResults() {

        int varIncreasesCategoryB = 0;
        int varDecreasesCategoryB = 0;
        int varIncreasesCategoryC = 0;
        int varDecreasesCategoryC = 0;
        NumberFormat formatter = new DecimalFormat("#0.0");
        for (PatientStatisticalChanges entry : categoryB_Var) {
            if (entry.getChange() < 0) varDecreasesCategoryB++;
            else if (entry.getChange() > 0) varIncreasesCategoryB++;
        }
        for (PatientStatisticalChanges entry : categoryC_Var) {
            if (entry.getChange() < 0) varDecreasesCategoryC++;
            else if (entry.getChange() > 0) varIncreasesCategoryC++;
        }

        int varIncreasesTotal = varIncreasesCategoryB + varIncreasesCategoryC +
                categoryD_Var.size() + categoryE_Var.size();
        int varDecreasesTotal = categoryA_Var.size() + varDecreasesCategoryB +
                varDecreasesCategoryC;
        System.out.println("VARIABILITY:");
        System.out.println("Scores of 115+, decreased by 15+: " + categoryA_Var.size());
        System.out.println("Scores 90+ increased by 12+: " + varIncreasesCategoryB);
        System.out.println("Scores 90+ decreased by 12+: " + varDecreasesCategoryB);
        System.out.println("Scores between 78 and 90 increased by 8+: " + varIncreasesCategoryC);
        System.out.println("Scores between 78 and 90 decreased by 8+: " + varDecreasesCategoryC);
        System.out.println("Scores below 90 increased to 90+ and by 8+: " + categoryD_Var.size());
        System.out.println("Scores below 70 increased to 78+: " + categoryE_Var.size());
        System.out.println("% of significant increases: " + formatter.format(
                (varIncreasesTotal / numberOfPatients)
                        * 100) + "%");
        System.out.println("% of significant decreases: " + formatter.format(
                (varDecreasesTotal / numberOfPatients)
                        * 100) + "%");

    }
}
