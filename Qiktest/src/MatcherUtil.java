import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Utility class that captures matches to extract test data from PDF
public class MatcherUtil {

    public static Matcher PERFORMANCE_PATTERN_MATCHER;
    public static Matcher ACCURACY_PATTERN_MATCHER;
    public static Matcher OMISSION_ERRORS_MATCHER;
    public static Matcher OUTLIER_RESPONSES_MATCHER;
    public static Matcher COMMISSION_ERRORS_MATCHER;
    public static Matcher RESPONSE_TIME_MATCHER;
    public static Matcher VARIABILITY_MATCHER;
    public static Matcher DATE_MATCHER;

    public static Matcher NAME_MATCHER;

    public static void getDataMatchers(String page) {

        PERFORMANCE_PATTERN_MATCHER = Pattern.compile(Constants.PERFORMANCE_PATTERN_REGEX).matcher(page);
        ACCURACY_PATTERN_MATCHER = Pattern.compile(Constants.ACCURACY_PATTERN_REGEX).matcher(page);
        OMISSION_ERRORS_MATCHER = Pattern.compile(Constants.OMISSION_ERRORS_REGEX).matcher(page);
        OUTLIER_RESPONSES_MATCHER = Pattern.compile(Constants.OUTLIER_RESPONSES_REGEX).matcher(page);
        COMMISSION_ERRORS_MATCHER = Pattern.compile(Constants.COMMISSION_ERRORS_REGEX).matcher(page);
        RESPONSE_TIME_MATCHER = Pattern.compile(Constants.RESPONSE_TIME_REGEX).matcher(page);
        VARIABILITY_MATCHER = Pattern.compile(Constants.VARIABILITY_REGEX).matcher(page);
        DATE_MATCHER = Pattern.compile(Constants.DATE_REGEX).matcher(page);

    }

    public static void getNameMatchers(String filename) {

        NAME_MATCHER = Pattern.compile(Constants.NAME_PATTERN).matcher(filename);

    }
}
