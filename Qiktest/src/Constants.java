public class Constants {

    public static final String TEST_FOLDER_PATH = "C:\\Users\\jermr\\Desktop\\Qiktest test";
    public static final String LIVE_FOLDER_PATH = "C:\\Users\\jermr\\QIK tests";
    public static final String PDFSTRIPPER_TEST_PATH = "C:\\Users\\jermr\\Desktop\\PDFStripper test";

    public static final String NAME_PATTERN = "(([A-Za-z]+)\\s*([A-Z][A-Za-z]+))(\\s|-|_|\\d*)[^pdf]";


    public static final String PERFORMANCE_PATTERN_REGEX = "[>]*(\\d+).*Performance\\s*Index";
    public static final String ACCURACY_PATTERN_REGEX = "[>]*(\\d+).*Accuracy\\s*Index";
    public static final String OMISSION_ERRORS_REGEX = "Omission Errors (\\d+) error[s]* \\d+.\\d+ errors >*(\\d+)";
    public static final String OUTLIER_RESPONSES_REGEX = "Outlier Responses (\\d+) error[s]* \\d+.\\d+ errors >*(\\d+)";
    public static final String COMMISSION_ERRORS_REGEX = "Commission Errors (\\d+) error[s]* \\d+.\\d+ errors >*(\\d+)";
    public static final String RESPONSE_TIME_REGEX = "Response Time (\\d+.\\d+) ms \\d+[.]* ms [>]*(\\d+)";
    public static final String VARIABILITY_REGEX = "Variability (\\d+.\\d+) ms \\d+[.]* ms [>]*(\\d+)";
    public static final String DATE_REGEX = "([A-z]{3}).? (\\d+), (\\d{4})";

    public static final String SAMPLE = " A Simple PDF File \r\n" +
            " This is a small demonstration .pdf file - \r\n" +
            " just for use in the Virtual Mechanics tutorials. More text. And more \r\n" +
            " text. And more text. And more text. And more text. \r\n" +
            " And more text. And more text. And more text. And more text. And more \r\n" +
            " text. And more text. Boring, zzzzz. And more text. And more text. And \r\n" +
            " more text. And more text. And more text. And more text. And more text. \r\n" +
            " And more text. And more text. \r\n" +
            " And more text. And more text. And more text. And more text. And more \r\n" +
            " text. And more text. And more text. Even more. Continued on page 2 ...\r\n";

}
