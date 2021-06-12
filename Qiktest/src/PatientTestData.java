import java.time.LocalDate;

// Contains all test data for ONE test for ONE patient. All raw and score data is captured, though only
// a portion of it is used in this program; might be a good idea to store it for unexpected need
// in the future

class PatientTestData implements Comparable<PatientTestData> {

    final private double performance;
    final private double accuracy;
    final private double omissions;
    final private double outliers;
    final private double commissions;
    final private double time;
    final private double var;
    final private double omissionScore;
    final private double outlierScore;
    final private double commissionScore;
    final private double timeScore;
    final private double varScore;
    final private LocalDate date;
    final private String name;

    public PatientTestData(String name, double performance, double accuracy, double omissions,
                           double omissionsScore, double outliers, double outliersScore,
                           double commissions, double commissionsScore, double time, double timeScore,
                           double variability, double variabilityScore, LocalDate date) {

        this.performance = performance;
        this.accuracy = accuracy;
        this.omissions = omissions;
        this.omissionScore = omissionsScore;
        this.outliers = outliers;
        this.outlierScore = outliersScore;
        this.commissions = commissions;
        this.commissionScore = commissionsScore;
        this.time = time;
        this.timeScore = timeScore;
        this.var = variability;
        this.varScore = variabilityScore;
        this.date = date;
        this.name = name;
    }

    public double getPerformance() {
        return performance;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public String getName() { return name; }

    public double getOmissions() { return omissions; }

    public double getOutliers() { return outliers; }

    public double getCommissions() { return commissions; }

    public double getVar() { return var; }

    public double getTime() { return time; }

    public double getOmissionScore() { return omissionScore; }

    public double getOutlierScore() { return outlierScore; }

    public double getCommissionScore() { return commissionScore; }

    public double getTimeScore() { return timeScore; }

    public double getVarScore() { return varScore; }

    public LocalDate getDate() { return date; }

    @Override
    public String toString() {

        return "[" + performance + ", " + accuracy + ", " + omissions + ", " + outliers
                + ", " + commissions + ", " + time + ", " + var + ", " + date + "]";

    }

    @Override
    public int compareTo (PatientTestData d) {

        if (this.name.equals(d.name)) {
            if (this.date.isBefore(d.date)) return -1;
            else if (this.date.isBefore(d.date)) return 1;
            else return 0;
        }

        else {
            return this.name.compareTo(d.name);
        }

    }

    @Override

    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        final PatientTestData d = (PatientTestData) obj;

        return (d.getDate().equals(this.getDate()) && d.getName().equals(this.getName()));
    }

}
