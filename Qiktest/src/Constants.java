public class Constants {

    public static final String TEST_FOLDER_PATH = "C:\\Users\\HRV2\\Desktop\\test";
    public static final String LIVE_FOLDER_PATH = "C:\\Users\\HRV2\\Dropbox\\CygnetSessions";

    public static final String NAME_PATTERN = "(([A-Za-z]+)\\s*([A-Z][A-Za-z]+))(\\s|-|_|\\d*)[^pdf]";


    public static final String PERFORMANCE_PATTERN_REGEX = "[>]*(\\d+).*Performance\\s*Index";
    public static final String ACCURACY_PATTERN_REGEX = "[>]*(\\d+).*Accuracy\\s*Index";
    public static final String OMISSION_ERRORS_REGEX = "Omission Errors (\\d+) error[s]* \\d+.\\d+ errors >*(\\d+)";
    public static final String OUTLIER_RESPONSES_REGEX = "Outlier Responses (\\d+) error[s]* \\d+.\\d+ errors >*(\\d+)";
    public static final String COMMISSION_ERRORS_REGEX = "Commission Errors (\\d+) error[s]* \\d+.\\d+ errors >*(\\d+)";
    public static final String RESPONSE_TIME_REGEX = "Response Time (\\d+.\\d+) ms \\d+[.]* ms [>]*(\\d+)";
    public static final String VARIABILITY_REGEX = "Variability (\\d+.\\d+) ms \\d+[.]* ms [>]*(\\d+)";
    public static final String DATE_REGEX = "([A-z]{3}). (\\d+), (\\d{4})";

}
