import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PrintUtil {

    private static StringBuilder allResults;

    // String that contains total number of times each category in each metric is satisfied

    public static StringBuilder getAllResults() {
        createStringBuilder();
        return allResults;
    }

    private static void createStringBuilder() {
        allResults = new StringBuilder();
        allResults.append(System.getProperty("line.separator"))
                .append("# of patients evaluated: ").append(DataUtil.numberOfPatients)
                .append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));
        appendOmissionResults();
        appendCommissionResults();
        appendVariabilityResults();
    }

    private static void appendOmissionResults() {

        int omissionIncreasesCategoryB = 0;
        int omissionDecreasesCategoryB = 0;
        int omissionIncreasesCategoryC = 0;
        int omissionDecreasesCategoryC = 0;
        NumberFormat formatter = new DecimalFormat("#0.0");
        for (PatientStatisticalChanges entry : DataUtil.categoryB_Omissions) {
            if (entry.getChange() < 0) omissionDecreasesCategoryB++;
            else if (entry.getChange() > 0) omissionIncreasesCategoryB++;
        }
        for (PatientStatisticalChanges entry : DataUtil.categoryC_Omissions) {
            if (entry.getChange() < 0) omissionDecreasesCategoryC++;
            else if (entry.getChange() > 0) omissionIncreasesCategoryC++;
        }

        int omissionIncreasesTotal = omissionIncreasesCategoryB + omissionIncreasesCategoryC +
                DataUtil.categoryD_Omissions.size() + DataUtil.categoryE_Omissions.size();
        int omissionDecreasesTotal = DataUtil.categoryA_Omissions.size() + omissionDecreasesCategoryB +
                omissionDecreasesCategoryC;


        allResults.append("OMISSIONS:").append(System.getProperty("line.separator"));
        allResults.append("Scores of 115+, decreased by 15+: ").append(DataUtil.categoryA_Omissions.size()).append(System.getProperty("line.separator"));
        allResults.append("Scores 90+ increased by 12+: ").append(omissionIncreasesCategoryB).append(System.getProperty("line.separator"));
        allResults.append("Scores 90+ decreased by 12+: ").append(omissionDecreasesCategoryB).append(System.getProperty("line.separator"));
        allResults.append("Scores between 78 and 90 increased by 8+: ").append(omissionIncreasesCategoryC).append(System.getProperty("line.separator"));
        allResults.append("Scores between 78 and 90 decreased by 8+: ").append(omissionDecreasesCategoryC).append(System.getProperty("line.separator"));
        allResults.append("Scores below 90 increased to 90+ and by 8+: ")
                .append(DataUtil.categoryD_Omissions.size()).append(System.getProperty("line.separator"));
        allResults.append("Scores below 70 increased to 78+: ").append(DataUtil.categoryE_Omissions.size()).append(System.getProperty("line.separator"));
        allResults.append("% of significant increases: ").append(formatter.format(
                (omissionIncreasesTotal / DataUtil.numberOfPatients)
                        * 100)).append("%").append(System.getProperty("line.separator"));
        allResults.append("% of significant decreases: ").append(formatter.format(
                (omissionDecreasesTotal / DataUtil.numberOfPatients)
                        * 100)).append("%").append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));

//        System.out.println();
//        System.out.println("# of patients evaluated: " + DataUtil.numberOfPatients);
//        System.out.println("OMISSIONS:");
//        System.out.println("Scores of 115+, decreased by 15+: " + DataUtil.categoryA_Omissions.size());
//        System.out.println("Scores 90+ increased by 12+: " + omissionIncreasesCategoryB);
//        System.out.println("Scores 90+ decreased by 12+: " + omissionDecreasesCategoryB);
//        System.out.println("Scores between 78 and 90 increased by 8+: " + omissionIncreasesCategoryC);
//        System.out.println("Scores between 78 and 90 decreased by 8+: " + omissionDecreasesCategoryC);
//        System.out.println("Scores below 90 increased to 90+ and by 8+: " + DataUtil.categoryD_Omissions.size());
//        System.out.println("Scores below 70 increased to 78+: " + DataUtil.categoryE_Omissions.size());
//        System.out.println("% of significant increases: " + formatter.format(
//                (omissionIncreasesTotal / DataUtil.numberOfPatients)
//                        * 100) + "%");
//        System.out.println("% of significant decreases: " + formatter.format(
//                (omissionDecreasesTotal / DataUtil.numberOfPatients)
//                        * 100) + "%");
//        System.out.println();

    }

    private static void appendCommissionResults() {

        int commissionIncreasesCategoryB = 0;
        int commissionDecreasesCategoryB = 0;
        int commissionIncreasesCategoryC = 0;
        int commissionDecreasesCategoryC = 0;
        NumberFormat formatter = new DecimalFormat("#0.0");
        for (PatientStatisticalChanges entry : DataUtil.categoryB_Commissions) {
            if (entry.getChange() < 0) commissionDecreasesCategoryB++;
            else if (entry.getChange() > 0) commissionIncreasesCategoryB++;
        }
        for (PatientStatisticalChanges entry : DataUtil.categoryC_Commissions) {
            if (entry.getChange() < 0) commissionDecreasesCategoryC++;
            else if (entry.getChange() > 0) commissionIncreasesCategoryC++;
        }

        int commissionIncreasesTotal = commissionIncreasesCategoryB + commissionIncreasesCategoryC +
                DataUtil.categoryD_Commissions.size() + DataUtil.categoryE_Commissions.size();
        int commissionDecreasesTotal = DataUtil.categoryA_Commissions.size() + commissionDecreasesCategoryB +
                commissionDecreasesCategoryC;

        allResults.append("COMMISSIONS:").append(System.getProperty("line.separator"));
        allResults.append("Scores of 115+, decreased by 15+: ").append(DataUtil.categoryA_Commissions.size()).append(System.getProperty("line.separator"));
        allResults.append("Scores 90+ increased by 12+: ").append(commissionIncreasesCategoryB).append(System.getProperty("line.separator"));
        allResults.append("Scores 90+ decreased by 12+: ").append(commissionDecreasesCategoryB).append(System.getProperty("line.separator"));
        allResults.append("Scores between 78 and 90 increased by 8+: ").append(commissionIncreasesCategoryC).append(System.getProperty("line.separator"));
        allResults.append("Scores between 78 and 90 decreased by 8+: ").append(commissionDecreasesCategoryC).append(System.getProperty("line.separator"));
        allResults.append("Scores below 90 increased to 90+ and by 8+: ")
                .append(DataUtil.categoryD_Commissions.size()).append(System.getProperty("line.separator"));
        allResults.append("Scores below 70 increased to 78+: ").append(DataUtil.categoryE_Commissions.size()).append(System.getProperty("line.separator"));
        allResults.append("% of significant increases: ").append(formatter.format(
                (commissionIncreasesTotal / DataUtil.numberOfPatients)
                        * 100)).append("%").append(System.getProperty("line.separator"));
        allResults.append("% of significant decreases: ").append(formatter.format(
                (commissionDecreasesTotal / DataUtil.numberOfPatients)
                        * 100)).append("%").append(System.getProperty("line.separator")).append(System.getProperty("line.separator"));


//        System.out.println("COMMISSIONS:");
//        System.out.println("Scores of 115+, decreased by 15+: " + DataUtil.categoryA_Commissions.size());
//        System.out.println("Scores 90+ increased by 12+: " + commissionIncreasesCategoryB);
//        System.out.println("Scores 90+ decreased by 12+: " + commissionDecreasesCategoryB);
//        System.out.println("Scores between 78 and 90 increased by 8+: " + commissionIncreasesCategoryC);
//        System.out.println("Scores between 78 and 90 decreased by 8+: " + commissionDecreasesCategoryC);
//        System.out.println("Scores below 90 increased to 90+ and by 8+: " + DataUtil.categoryD_Commissions.size());
//        System.out.println("Scores below 70 increased to 78+: " + DataUtil.categoryE_Commissions.size());
//        System.out.println("% of significant increases: " + formatter.format(
//                (commissionIncreasesTotal / DataUtil.numberOfPatients)
//                        * 100) + "%");
//        System.out.println("% of significant decreases: " + formatter.format(
//                (commissionDecreasesTotal / DataUtil.numberOfPatients)
//                        * 100) + "%");
//        System.out.println();

    }

    private static void appendVariabilityResults() {

        int varIncreasesCategoryB = 0;
        int varDecreasesCategoryB = 0;
        int varIncreasesCategoryC = 0;
        int varDecreasesCategoryC = 0;
        NumberFormat formatter = new DecimalFormat("#0.0");
        for (PatientStatisticalChanges entry : DataUtil.categoryB_Var) {
            if (entry.getChange() < 0) varDecreasesCategoryB++;
            else if (entry.getChange() > 0) varIncreasesCategoryB++;
        }
        for (PatientStatisticalChanges entry : DataUtil.categoryC_Var) {
            if (entry.getChange() < 0) varDecreasesCategoryC++;
            else if (entry.getChange() > 0) varIncreasesCategoryC++;
        }

        int varIncreasesTotal = varIncreasesCategoryB + varIncreasesCategoryC +
                DataUtil.categoryD_Var.size() + DataUtil.categoryE_Var.size();
        int varDecreasesTotal = DataUtil.categoryA_Var.size() + varDecreasesCategoryB +
                varDecreasesCategoryC;

        allResults.append("VARIABILITY:").append(System.getProperty("line.separator"));
        allResults.append("Scores of 115+, decreased by 15+: ").append(DataUtil.categoryA_Var.size()).append(System.getProperty("line.separator"));
        allResults.append("Scores 90+ increased by 12+: ").append(varIncreasesCategoryB).append(System.getProperty("line.separator"));
        allResults.append("Scores 90+ decreased by 12+: ").append(varDecreasesCategoryB).append(System.getProperty("line.separator"));
        allResults.append("Scores between 78 and 90 increased by 8+: ").append(varIncreasesCategoryC).append(System.getProperty("line.separator"));
        allResults.append("Scores between 78 and 90 decreased by 8+: ").append(varDecreasesCategoryC).append(System.getProperty("line.separator"));
        allResults.append("Scores below 90 increased to 90+ and by 8+: ")
                .append(DataUtil.categoryD_Var.size()).append(System.getProperty("line.separator"));
        allResults.append("Scores below 70 increased to 78+: ").append(DataUtil.categoryE_Var.size()).append(System.getProperty("line.separator"));
        allResults.append("% of significant increases: ").append(formatter.format(
                (varIncreasesTotal / DataUtil.numberOfPatients)
                        * 100)).append("%").append(System.getProperty("line.separator"));
        allResults.append("% of significant decreases: ").append(formatter.format(
                (varDecreasesTotal / DataUtil.numberOfPatients)
                        * 100)).append("%");


//        System.out.println("VARIABILITY:");
//        System.out.println("Scores of 115+, decreased by 15+: " + DataUtil.categoryA_Var.size());
//        System.out.println("Scores 90+ increased by 12+: " + varIncreasesCategoryB);
//        System.out.println("Scores 90+ decreased by 12+: " + varDecreasesCategoryB);
//        System.out.println("Scores between 78 and 90 increased by 8+: " + varIncreasesCategoryC);
//        System.out.println("Scores between 78 and 90 decreased by 8+: " + varDecreasesCategoryC);
//        System.out.println("Scores below 90 increased to 90+ and by 8+: " + DataUtil.categoryD_Var.size());
//        System.out.println("Scores below 70 increased to 78+: " + DataUtil.categoryE_Var.size());
//        System.out.println("% of significant increases: " + formatter.format(
//                (varIncreasesTotal / DataUtil.numberOfPatients)
//                        * 100) + "%");
//        System.out.println("% of significant decreases: " + formatter.format(
//                (varDecreasesTotal / DataUtil.numberOfPatients)
//                        * 100) + "%");

    }
}
