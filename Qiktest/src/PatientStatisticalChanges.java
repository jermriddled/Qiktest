
// Contains standard score data for ONE metric (omissions, commissions, or variability) for ONE patient

class PatientStatisticalChanges {

    private final String name;
    private final double before;
    private final double after;
    private final double change;

    public PatientStatisticalChanges(String name, double before, double after, double change) {

        this.name = name;
        this.before = before;
        this.after = after;
        this.change = change;

    }

    public double getChange() { return change; }

}
