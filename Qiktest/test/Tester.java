import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class Tester {

    @Test
    public void testGetAllFiles() {
        File file1 = new File("C:\\Users\\jermr\\Desktop\\Qiktest test\\Jeremy Steinberg-12511.pdf");
        File file2 = new File("C:\\Users\\jermr\\Desktop\\Qiktest test\\Jeremy Steinberg-12_08_2015.pdf");
        File file3 = new File("C:\\Users\\jermr\\Desktop\\Qiktest test\\Jeremy Steinberg-02_04_2018.pdf");
        File[] testFileArray = {file1, file2, file3};
        Arrays.sort(testFileArray);
        assertArrayEquals(testFileArray, FileUtil.getAllFiles(Constants.TEST_FOLDER_PATH));
    }

    @Test
    public void testGetPDFText() throws IOException {
        File[] file = FileUtil.getAllFiles(Constants.PDFSTRIPPER_TEST_PATH);
        String page = FileUtil.getPDFText(file, 0);
        assertEquals(Constants.SAMPLE, page);
    }

    @Test
    public void testCollectAllPatientData() throws IOException {
        DataUtil.main(new String[]{Constants.TEST_FOLDER_PATH});
        File[] files = FileUtil.getAllFiles(Constants.TEST_FOLDER_PATH);
        int numberOfFiles = files.length;
        String expectedPatientName = "Jeremy Steinberg";

        String patientNameTest1 = DataUtil.data.get(expectedPatientName).get(0).getName();
        String patientNameTest2 = DataUtil.data.get(expectedPatientName).get(1).getName();
        double omissionScoreTest1 = DataUtil.data.get(expectedPatientName).get(0).getOmissionScore();
        double omissionScoreTest2 = DataUtil.data.get(expectedPatientName).get(1).getOmissionScore();
        double commissionScoreTest1 = DataUtil.data.get(expectedPatientName).get(0).getCommissionScore();
        double commissionScoreTest2 = DataUtil.data.get(expectedPatientName).get(1).getCommissionScore();
        double variabilityScoreTest1 = DataUtil.data.get(expectedPatientName).get(0).getVarScore();
        double variabilityScoreTest2 = DataUtil.data.get(expectedPatientName).get(1).getVarScore();
        LocalDate dateTest1 = DataUtil.data.get(expectedPatientName).get(0).getDate();
        LocalDate dateTest2 = DataUtil.data.get(expectedPatientName).get(1).getDate();


        assertEquals(expectedPatientName, patientNameTest1);
        assertEquals(expectedPatientName, patientNameTest2);
        assertEquals(109, omissionScoreTest1, 0);
        assertEquals(125, commissionScoreTest1, 0);
        assertEquals(145, variabilityScoreTest1, 0);
        assertEquals(LocalDate.of(2015, 8, 12), dateTest1);
        assertEquals(108, omissionScoreTest2, 0);
        assertEquals(124, commissionScoreTest2, 0);
        assertEquals(145, variabilityScoreTest2, 0);
        assertEquals(LocalDate.of(2018, 4, 2), dateTest2);
        assertEquals(1, DataUtil.data.size());

    }


    @Test
    public void testEvaluateAll() {

        PatientTestData baseline115DecreaseBy15 = new PatientTestData("baselineA", 0.0, 0.0, 0.0,
                115.0, 0.0, 0.0, 0.0, 115.0, 0.0,
                0.0, 0.0, 115.0, LocalDate.of(2000, 1, 1));
        PatientTestData subsequent115DecreaseBy15 = new PatientTestData("baselineA", 0.0, 0.0, 0.0,
                100.0, 0.0, 0.0, 0.0, 100.0, 0.0,
                0.0, 0.0, 100.0, LocalDate.of(2000, 1, 2));
        PatientTestData baseline90IncreaseBy12 = new PatientTestData("baselineB", 0.0, 0.0, 0.0,
                90.0, 0.0, 0.0, 0.0, 90.0, 0.0,
                0.0, 0.0, 90.0, LocalDate.of(2000, 1, 1));
        PatientTestData subsequent90IncreaseBy12 = new PatientTestData("baselineB", 0.0, 0.0, 0.0,
                102.0, 0.0, 0.0, 0.0, 102.0, 0.0,
                0.0, 0.0, 102.0, LocalDate.of(2000, 1, 2));
        PatientTestData baseline90DecreaseBy12 = new PatientTestData("baselineC", 0.0, 0.0, 0.0,
                90.0, 0.0, 0.0, 0.0, 90.0, 0.0,
                0.0, 0.0, 90.0, LocalDate.of(2000, 1, 1));
        PatientTestData subsequent90DecreaseBy12 = new PatientTestData("baselineC", 0.0, 0.0, 0.0,
                78.0, 0.0, 0.0, 0.0, 78.0, 0.0,
                0.0, 0.0, 78.0, LocalDate.of(2000, 1, 2));
        PatientTestData baseline90IncreaseBy8 = new PatientTestData("baselineD", 0.0, 0.0, 0.0,
                90.0, 0.0, 0.0, 0.0, 90.0, 0.0,
                0.0, 0.0, 90.0, LocalDate.of(2000, 1, 1));
        PatientTestData subsequent90IncreaseBy8 = new PatientTestData("baselineD", 0.0, 0.0, 0.0,
                98.0, 0.0, 0.0, 0.0, 98.0, 0.0,
                0.0, 0.0, 98.0, LocalDate.of(2000, 1, 2));
        PatientTestData baseline90DecreaseBy8 = new PatientTestData("baselineE", 0.0, 0.0, 0.0,
                90.0, 0.0, 0.0, 0.0, 90.0, 0.0,
                0.0, 0.0, 90.0, LocalDate.of(2000, 1, 1));
        PatientTestData subsequent90DecreaseBy8 = new PatientTestData("baselineE", 0.0, 0.0, 0.0,
                82.0, 0.0, 0.0, 0.0, 82.0, 0.0,
                0.0, 0.0, 82.0, LocalDate.of(2000, 1, 2));
        PatientTestData baselineBelow90IncreaseAbove90By8 = new PatientTestData("baselineF", 0.0, 0.0, 0.0,
                80.0, 0.0, 0.0, 0.0, 80.0, 0.0,
                0.0, 0.0, 80.0, LocalDate.of(2000, 1, 1));
        PatientTestData subsequentBelow90IncreaseAbove90By8 = new PatientTestData("baselineF", 0.0, 0.0, 0.0,
                92.0, 0.0, 0.0, 0.0, 92.0, 0.0,
                0.0, 0.0, 92.0, LocalDate.of(2000, 1, 2));
        PatientTestData baselineBelow70IncreaseAbove78 = new PatientTestData("baselineG", 0.0, 0.0, 0.0,
                65.0, 0.0, 0.0, 0.0, 65.0, 0.0,
                0.0, 0.0, 65.0, LocalDate.of(2000, 1, 1));
        PatientTestData subsequentBelow70IncreaseAbove78 = new PatientTestData("baselineG", 0.0, 0.0, 0.0,
                78.0, 0.0, 0.0, 0.0, 78.0, 0.0,
                0.0, 0.0, 78.0, LocalDate.of(2000, 1, 2));

        ArrayList<PatientTestData> baselineA = new ArrayList<>();
        baselineA.add(baseline115DecreaseBy15);
        baselineA.add(subsequent115DecreaseBy15);
        ArrayList<PatientTestData> baselineB = new ArrayList<>();
        baselineB.add(baseline90IncreaseBy12);
        baselineB.add(subsequent90IncreaseBy12);
        ArrayList<PatientTestData> baselineC = new ArrayList<>();
        baselineC.add(baseline90DecreaseBy12);
        baselineC.add(subsequent90DecreaseBy12);
        ArrayList<PatientTestData> baselineD = new ArrayList<>();
        baselineD.add(baseline90IncreaseBy8);
        baselineD.add(subsequent90IncreaseBy8);
        ArrayList<PatientTestData> baselineE = new ArrayList<>();
        baselineE.add(baseline90DecreaseBy8);
        baselineE.add(subsequent90DecreaseBy8);
        ArrayList<PatientTestData> baselineF = new ArrayList<>();
        baselineF.add(baselineBelow90IncreaseAbove90By8);
        baselineF.add(subsequentBelow90IncreaseAbove90By8);
        ArrayList<PatientTestData> baselineG = new ArrayList<>();
        baselineG.add(baselineBelow70IncreaseAbove78);
        baselineG.add(subsequentBelow70IncreaseAbove78);


        DataUtil.data = new HashMap<>();
        DataUtil.wipeDataStructures();

        DataUtil.data.put("baselineA", baselineA);
        DataUtil.data.put("baselineB", baselineB);
        DataUtil.data.put("baselineC", baselineC);
        DataUtil.data.put("baselineD", baselineD);
        DataUtil.data.put("baselineE", baselineE);
        DataUtil.data.put("baselineF", baselineF);
        DataUtil.data.put("baselineG", baselineG);

        EvaluationUtil evaluator = new EvaluationUtil(new SignificantSubsequentOmissionScore(),
                new SignificantSubsequentCommissionScore(), new SignificantSubsequentVariabilityScore());
        evaluator.evaluateAll();

        assertEquals(1, DataUtil.categoryAPatients.get(0).size());
        assertEquals(1, DataUtil.categoryAPatients.get(1).size());
        assertEquals(1, DataUtil.categoryAPatients.get(2).size());
        assertEquals(1, DataUtil.categoryBDecreasePatients.get(0).size());
        assertEquals(1, DataUtil.categoryBIncreasePatients.get(0).size());
        assertEquals(0, DataUtil.categoryCDecreasePatients.get(0).size());
        assertEquals(1, DataUtil.categoryCIncreasePatients.get(0).size());
        assertEquals(1, DataUtil.categoryDPatients.get(0).size());
        assertEquals(1, DataUtil.categoryDPatients.get(1).size());
        assertEquals(1, DataUtil.categoryDPatients.get(2).size());
        assertEquals(1, DataUtil.categoryEPatients.get(0).size());
        assertEquals(1, DataUtil.categoryEPatients.get(1).size());
        assertEquals(1, DataUtil.categoryEPatients.get(2).size());

    }

/*    @Test

    public void testGetBestSignificantScore() {
        Map<String, ArrayList<PatientTestData>> patientData = new HashMap<>();

    }*/

}
