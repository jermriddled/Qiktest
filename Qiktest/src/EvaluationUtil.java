import java.util.ArrayList;
import java.util.Map;

// Utility class that makes statistical evaluations of patients' test data.
public class EvaluationUtil {

    SignificantSubsequentOmissionScore significantSubsequentOmissionScore;
    SignificantSubsequentCommissionScore significantSubsequentCommissionScore;
    SignificantSubsequentVariabilityScore significantSubsequentVariabilityScore;

    public EvaluationUtil(SignificantSubsequentOmissionScore omission, SignificantSubsequentCommissionScore commission,
                          SignificantSubsequentVariabilityScore variability) {

        this.significantSubsequentOmissionScore = omission;
        this.significantSubsequentCommissionScore = commission;
        this.significantSubsequentVariabilityScore = variability;

    }

    public void evaluateAll() {
        for (Map.Entry<String, ArrayList<PatientTestData>> entry: DataUtil.data.entrySet()) {

            evaluateOne(entry);

        }
    }

    // Makes comparisons between qiktest scores; finds subsequent test with greatest difference
    // in absolute value from baseline; uses evaluate() method on each patient's two tests
    private void evaluateOne(Map.Entry<String, ArrayList<PatientTestData>> entry) {

        String patientName = entry.getValue().get(0).getName();

        // Baseline data from first qiktest
        double baselineOmissionScore = entry.getValue().get(0).getOmissionScore();
        double baselineCommissionScore = entry.getValue().get(0).getCommissionScore();
        double baselineVarScore = entry.getValue().get(0).getVarScore();

        // Significant subsequent score is determined by the test with the highest absolute value
        // difference from baseline score
        double significantSubsequentOmissionScore = this.significantSubsequentOmissionScore.calculateScore(entry);
        double significantSubsequentCommissionScore = this.significantSubsequentCommissionScore.calculateScore(entry);
        double significantSubsequentVarScore = this.significantSubsequentVariabilityScore.calculateScore(entry);

        // Omissions evaluated here
        double omissionScoreChanges = significantSubsequentOmissionScore - baselineOmissionScore;
        evaluateOmissions(patientName, baselineOmissionScore, omissionScoreChanges,
                significantSubsequentOmissionScore, new PatientStatisticalChanges(patientName,
                        baselineOmissionScore, significantSubsequentOmissionScore, omissionScoreChanges));

        // Commissions evaluated here
        double commissionScoreChanges = significantSubsequentCommissionScore -
                baselineCommissionScore;
        evaluateCommissions(patientName, baselineCommissionScore, commissionScoreChanges,
                significantSubsequentCommissionScore, new PatientStatisticalChanges(patientName,
                        baselineCommissionScore,  significantSubsequentCommissionScore,
                        commissionScoreChanges));

        // Variability evaluated here
        double varScoreChanges = significantSubsequentVarScore - baselineVarScore;
        evaluateVariability(patientName, baselineVarScore, varScoreChanges,
                significantSubsequentVarScore, new PatientStatisticalChanges(patientName,
                        baselineVarScore, significantSubsequentVarScore, varScoreChanges));


    }

    // Evaluates if a patient's test data satisfies a specific scoring category
    private static void evaluateOmissions(String name, double baseline, double changes,
                                          double subsequent, PatientStatisticalChanges stats) {


        if (baseline >= 115 && changes <= -15) {
            DataUtil.categoryA_Omissions.add(stats);
            DataUtil.categoryAPatients.get(0).add(name);
        } else if (baseline >= 90) {
            if (changes <= -12 || changes >= 12) {
                DataUtil.categoryB_Omissions.add(stats);
            }
            if (changes <= -12) {
                DataUtil.categoryBDecreasePatients.get(0).add(name);
            }
            else if (changes >= 12) {
                DataUtil.categoryBIncreasePatients.get(0).add(name);
            }
        } else if (baseline < 90 && baseline >= 78) {
            if (changes <= -8 || changes >= 8) {
                DataUtil.categoryC_Omissions.add(stats);
            }
            if (changes <= -8) {
                DataUtil.categoryCDecreasePatients.get(0).add(name);
            }
            else if (changes >= 8) {
                DataUtil.categoryCIncreasePatients.get(0).add(name);
            }
        }
        if (baseline < 90 && subsequent >= 90 && changes >= 8) {
            DataUtil.categoryD_Omissions.add(stats);
            DataUtil.categoryDPatients.get(0).add(name);
        }
        if (baseline <= 70 && subsequent >= 78) {
            DataUtil.categoryE_Omissions.add(stats);
            DataUtil.categoryEPatients.get(0).add(name);
        }

    }

    // Evaluates if a patient's test data satisfies a specific scoring category
    private static void evaluateCommissions(String name, double baseline, double changes,
                                            double subsequent, PatientStatisticalChanges stats) {

        if (baseline >= 115 && changes <= -15) {
            DataUtil.categoryA_Commissions.add(stats);
            DataUtil.categoryAPatients.get(1).add(name);
        } else if (baseline >= 90) {
            if (changes <= -12 || changes >= 12) {
                DataUtil.categoryB_Commissions.add(stats);
            }
            if (changes <= -12) {
                DataUtil.categoryBDecreasePatients.get(1).add(name);
            } else if (changes >= 12) {
                DataUtil.categoryBIncreasePatients.get(1).add(name);
            }
        } else if (baseline < 90 && baseline >= 78) {
            if (changes <= -8 || changes >= 8) {
                DataUtil.categoryC_Commissions.add(stats);
            }
            if (changes <= -8) {
                DataUtil.categoryCDecreasePatients.get(1).add(name);
            } else if (changes >= 8) {
                DataUtil.categoryCIncreasePatients.get(1).add(name);
            }
        }
        if (baseline < 90 && subsequent >= 90 && changes >= 8) {
            DataUtil.categoryD_Commissions.add(stats);
            DataUtil.categoryDPatients.get(1).add(name);
        }
        if (baseline <= 70 && subsequent >= 78) {
            DataUtil.categoryE_Commissions.add(stats);
            DataUtil.categoryEPatients.get(1).add(name);
        }

    }

    // Evaluates if a patient's test data satisfies a specific scoring category
    private static void evaluateVariability(String name, double baseline, double changes,
                                            double subsequent, PatientStatisticalChanges stats) {

        if (baseline >= 115 && changes <= -15) {
            DataUtil.categoryA_Var.add(stats);
            DataUtil.categoryAPatients.get(2).add(name);
        } else if (baseline >= 90) {
            if (changes <= -12 || changes >= 12) {
                DataUtil.categoryB_Var.add(stats);
            }
            if (changes <= -12) {
                DataUtil.categoryBDecreasePatients.get(2).add(name);
            }
            else if (changes >= 12) {
                DataUtil.categoryBIncreasePatients.get(2).add(name);
            }
        } else if (baseline < 90 && baseline >= 78) {
            if (changes <= -8 || changes >= 8) {
                DataUtil.categoryC_Var.add(stats);
            }
            if (changes <= -8) {
                DataUtil.categoryCDecreasePatients.get(2).add(name);
            }
            else if (changes >= 8) {
                DataUtil.categoryCIncreasePatients.get(2).add(name);
            }
        }
        if (baseline < 90 && subsequent >= 90 && changes >= 8) {
            DataUtil.categoryD_Var.add(stats);
            DataUtil.categoryDPatients.get(2).add(name);
        }
        if (baseline <= 70 && subsequent >= 78) {
            DataUtil.categoryE_Var.add(stats);
            DataUtil.categoryEPatients.get(2).add(name);
        }

    }

    // Compares test scores and finds most significant score
    public static double getBestSignificantScore(Map.Entry<String,
            ArrayList<PatientTestData>> entry, String category) {

        int size = entry.getValue().size();
        double bestScore = 0;

        switch (category) {
            case "Omission" -> {
                for (int i = 1; i < size; i++) {
                    double thisScore = entry.getValue().get(i).getOmissionScore();
                    if (thisScore > bestScore) {
                        bestScore = thisScore;
                    }
                }
            }
            case "Commission" -> {
                for (int i = 1; i < size; i++) {
                    double thisScore = entry.getValue().get(i).getCommissionScore();
                    if (thisScore > bestScore) {
                        bestScore = thisScore;
                    }
                }
            }
            case "Variability" -> {
                for (int i = 1; i < size; i++) {
                    double thisScore = entry.getValue().get(i).getVarScore();
                    if (thisScore > bestScore) {
                        bestScore = thisScore;
                    }
                }
            }

        }
        return bestScore;

    }
}
