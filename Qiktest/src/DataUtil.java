import java.io.File;
import java.io.IOException;
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
        collectAllPatientData(args[0]);
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

    public static void wipeDataStructures() {

        categoryA_Omissions = new ArrayList<>();
        categoryA_Commissions = new ArrayList<>();
        categoryA_Var = new ArrayList<>();
        categoryB_Omissions = new ArrayList<>();
        categoryB_Commissions = new ArrayList<>();
        categoryB_Var = new ArrayList<>();
        categoryC_Omissions = new ArrayList<>();
        categoryC_Commissions = new ArrayList<>();
        categoryC_Var = new ArrayList<>();
        categoryD_Omissions = new ArrayList<>();
        categoryD_Commissions = new ArrayList<>();
        categoryD_Var = new ArrayList<>();
        categoryE_Omissions = new ArrayList<>();
        categoryE_Commissions = new ArrayList<>();
        categoryE_Var = new ArrayList<>();
        categoryAPatients = new ArrayList<>();
        categoryBIncreasePatients = new ArrayList<>();
        categoryBDecreasePatients = new ArrayList<>();
        categoryCIncreasePatients = new ArrayList<>();
        categoryCDecreasePatients = new ArrayList<>();
        categoryDPatients = new ArrayList<>();
        categoryEPatients = new ArrayList<>();
        addDataStructures();

    }

    private static void addDataStructures() {

        // omissions = 0, commissions = 1, var = 2

        categoryAPatients.add(new ArrayList<>());
        categoryAPatients.add(new ArrayList<>());
        categoryAPatients.add(new ArrayList<>());
        categoryBIncreasePatients.add(new ArrayList<>());
        categoryBIncreasePatients.add(new ArrayList<>());
        categoryBIncreasePatients.add(new ArrayList<>());
        categoryBDecreasePatients.add(new ArrayList<>());
        categoryBDecreasePatients.add(new ArrayList<>());
        categoryBDecreasePatients.add(new ArrayList<>());
        categoryCIncreasePatients.add(new ArrayList<>());
        categoryCIncreasePatients.add(new ArrayList<>());
        categoryCIncreasePatients.add(new ArrayList<>());
        categoryCDecreasePatients.add(new ArrayList<>());
        categoryCDecreasePatients.add(new ArrayList<>());
        categoryCDecreasePatients.add(new ArrayList<>());
        categoryDPatients.add(new ArrayList<>());
        categoryDPatients.add(new ArrayList<>());
        categoryDPatients.add(new ArrayList<>());
        categoryEPatients.add(new ArrayList<>());
        categoryEPatients.add(new ArrayList<>());
        categoryEPatients.add(new ArrayList<>());

        // "data" is the collection of all test data for all patients who have taken more than one qiktest
        data = new HashMap<>();

    }

    // Determines if a patient has more than one test on file; if so, collect their data and store it
    private static int collectOnePatientData(int i, int numberOfFiles, File[] listFiles) {

        // List for storing patient scores
        ArrayList<PatientTestData> list = new ArrayList<>();

        String nameSpace = "@)*$)*@$@*!@%@%)*@!%*)!";
        String nameNoSpace = "@)*$)*@$@*!@%@%)*@!%*)!";

        String filename = listFiles[i].getName();
        MatcherUtil.getNameMatchers(filename);
        int numberOfSubsequentTests = 0;


        // Corner case for last file
        if (i == numberOfFiles - 1) {
            if (filename.contains(nameSpace) ||
                    filename.contains(nameNoSpace)) {
                try {
                    PatientTestData scores = getScoresArray(listFiles, i, nameSpace);
                    if (scores == null) return numberOfSubsequentTests;
                    list.add(scores);
                    Collections.sort(list);
                    data.put(nameSpace, list);
                } catch (IOException ignored) {
                }
            }
            return numberOfSubsequentTests;
        }

        // Adds this qiktest if patient has one more qiktest after this one on file
        if (MatcherUtil.NAME_MATCHER.find()) {
            nameSpace = MatcherUtil.NAME_MATCHER.group(2) + " " + MatcherUtil.NAME_MATCHER.group(3);
            nameNoSpace = MatcherUtil.NAME_MATCHER.group(2) + MatcherUtil.NAME_MATCHER.group(3);
        }
        while (i < numberOfFiles- 1 && (listFiles[i + 1].getName().contains(nameSpace) ||
                listFiles[i + 1].getName().contains(nameNoSpace))) {
            try {
                PatientTestData scores = getScoresArray(listFiles, i, nameSpace);
                if (scores == null) {
                    i++;
                    continue;
                }
                numberOfSubsequentTests++;
                i++;
                list.add(scores);
            } catch (IOException ignored) {
                numberOfSubsequentTests++;
                i++;
            }
        }

            /* Adds this qiktest if patient has multiple qiktests on file and this is
            the last one*/
        if (numberOfSubsequentTests > 0) {
            try {
                PatientTestData scores = getScoresArray(listFiles, i, nameSpace);
                if (scores == null) return numberOfSubsequentTests;
                list.add(scores);
                Collections.sort(list);
                data.put(nameSpace, list);
                numberOfSubsequentTests++;
            } catch (IOException ignored) {
            }

        }

        return numberOfSubsequentTests;
    }

    // Collects test files of all patients that have more than one comparable test on file
    public static void collectAllPatientData(String path) {

        File[] listFiles = FileUtil.getAllFiles(path);

        if (listFiles != null) {
            int numberOfFiles = listFiles.length;
            for (int i = 0; i < numberOfFiles; i++) {
                i+= collectOnePatientData(i,numberOfFiles, listFiles);
            }
        }
    }

    // Collects raw data from PDF
    private static Map<String, Double> parseRawData() {

        Map<String, Double> rawData = new HashMap<>();

        if (MatcherUtil.PERFORMANCE_PATTERN_MATCHER.find())
            rawData.put("performance", Double.parseDouble(MatcherUtil.PERFORMANCE_PATTERN_MATCHER.group(1)));
        if (MatcherUtil.ACCURACY_PATTERN_MATCHER.find())
            rawData.put("accuracy", Double.parseDouble(MatcherUtil.ACCURACY_PATTERN_MATCHER.group(1)));
        if (MatcherUtil.OMISSION_ERRORS_MATCHER.find()) {
            rawData.put("omissions", Double.parseDouble(MatcherUtil.OMISSION_ERRORS_MATCHER.group(1)));
            rawData.put("omissionsScore", Double.parseDouble(MatcherUtil.OMISSION_ERRORS_MATCHER.group(2)));
        }
        if (MatcherUtil.OUTLIER_RESPONSES_MATCHER.find()) {
            rawData.put("outliers", Double.parseDouble(MatcherUtil.OUTLIER_RESPONSES_MATCHER.group(1)));
            rawData.put("outliersScore", Double.parseDouble(MatcherUtil.OUTLIER_RESPONSES_MATCHER.group(2)));
        }
        if (MatcherUtil.COMMISSION_ERRORS_MATCHER.find()) {
            rawData.put("commissions", Double.parseDouble(MatcherUtil.COMMISSION_ERRORS_MATCHER.group(1)));
            rawData.put("commissionsScore", Double.parseDouble(MatcherUtil.COMMISSION_ERRORS_MATCHER.group(2)));
        }
        if (MatcherUtil.RESPONSE_TIME_MATCHER.find()) {
            rawData.put("time", Double.parseDouble(MatcherUtil.RESPONSE_TIME_MATCHER.group(1)));
            rawData.put("timeScore", Double.parseDouble(MatcherUtil.RESPONSE_TIME_MATCHER.group(2)));
        }
        if (MatcherUtil.VARIABILITY_MATCHER.find()) {
            rawData.put("var", Double.parseDouble(MatcherUtil.VARIABILITY_MATCHER.group(1)));
            rawData.put("varScore", Double.parseDouble(MatcherUtil.VARIABILITY_MATCHER.group(2)));
        }

        return rawData;
    }

    // Collects date of test from PDF
    private static LocalDate parseDate() {

        LocalDate testDate = null;
        if (MatcherUtil.DATE_MATCHER.find()) {
            int a = Integer.parseInt(MatcherUtil.DATE_MATCHER.group(3));
            int b = months.get(MatcherUtil.DATE_MATCHER.group(1));
            int c = Integer.parseInt(MatcherUtil.DATE_MATCHER.group(2));
            testDate = LocalDate.of(Integer.parseInt(MatcherUtil.DATE_MATCHER.group(3)),
                    months.get(MatcherUtil.DATE_MATCHER.group(1)),
                    Integer.parseInt(MatcherUtil.DATE_MATCHER.group(2)));
        }
        return testDate;

    }


    // Adds scores, dates, and name to PatientTestData object and returns it
    private static PatientTestData getScoresArray (File[] listFiles, int i, String n) throws IOException {

        String page = FileUtil.getPDFText(listFiles, i);
        MatcherUtil.getDataMatchers(page);
        Map<String, Double> rawData = parseRawData();
        if (rawData.size() != 12) return null;

        double performance = rawData.get("performance");
        double accuracy = rawData.get("accuracy");
        double omissions = rawData.get("omissions");
        double omissionsScore = rawData.get("omissionsScore");
        double outliers = rawData.get("outliers");
        double outliersScore = rawData.get("outliersScore");
        double commissions = rawData.get("commissions");
        double commissionsScore = rawData.get("commissionsScore");
        double time = rawData.get("time");
        double timeScore = rawData.get("timeScore");
        double var = rawData.get("var");
        double varScore = rawData.get("varScore");
        LocalDate testDate = parseDate();
        LocalDate cutoff = LocalDate.of(2015, 1, 2); // cut off for old tests

        // Return null if test is in old format
        if (testDate == null) return null;
        if (testDate.isBefore(cutoff)) return null;

        return new PatientTestData(n, performance, accuracy, omissions, omissionsScore, outliers,
                outliersScore, commissions, commissionsScore, time, timeScore, var, varScore, testDate);

    }

}
