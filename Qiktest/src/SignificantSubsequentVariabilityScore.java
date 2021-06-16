import java.util.ArrayList;
import java.util.Map;

public class SignificantSubsequentVariabilityScore {

    // Finds the subsequent test with the most significant increase or decrease in Variability score
    public double calculateScore(Map.Entry<String,
            ArrayList<PatientTestData>> entry) {

        int size = entry.getValue().size();
        if (size == 2) return entry.getValue().get(1).getVarScore();
        else return EvaluationUtil.getBestSignificantScore(entry, "Variability");

    }
}
