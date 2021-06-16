/*
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tester {


    @Test
    public void testCollectFiles() throws IOException {

        DataUtil.main(null);
        assertEquals(1, DataUtil.data.size());
        assertEquals(2, DataUtil.data.get("Jeremy Steinberg").size());

    }

    @Test
    public void testGetScoresArray() throws IOException {

        DataUtil.main(null);
        PatientTestData testData = DataUtil.data.get("Jeremy Steinberg").get(1);
        assertEquals(119, testData.getPerformance());
        assertEquals(130, testData.getAccuracy());
        assertEquals(0, testData.getOmissions());
        assertEquals(108, testData.getOmissionScore());
        assertEquals(0, testData.getOutliers());
        assertEquals(110, testData.getOutlierScore());
        assertEquals(0, testData.getCommissions());
        assertEquals(124, testData.getCommissionScore());
        assertEquals(335.0, testData.getTime());
        assertEquals(107, testData.getTimeScore());
        assertEquals(27.7, testData.getVar());
        assertEquals(145, testData.getVarScore());
        assertEquals(LocalDate.of(2018, 4, 2), testData.getDate());

        PatientTestData expectedTestData = new PatientTestData("Jeremy Steinberg",
                119, 130, 0, 108, 0, 110,
                0, 124, 335.0, 107, 27.7, 145,
                LocalDate.of(2018, 4, 2));
//        assertTrue(expectedTestData, DataUtil.data.get("Jeremy Steinberg").get(1));
        assertEquals(expectedTestData, DataUtil.data.get("Jeremy Steinberg").get(1));

    }

    @Test
    public void testEvaluateAll() throws IOException {

        DataUtil.main(null);

        assertEquals(0, DataUtil.categoryA_Omissions.size());
        assertEquals(0, DataUtil.categoryAPatients.get(0).size());
        assertEquals(0, DataUtil.categoryB_Omissions.size());
        assertEquals(0, DataUtil.categoryBDecreasePatients.get(0).size());
        assertEquals(0, DataUtil.categoryBIncreasePatients.get(0).size());
        assertEquals(0, DataUtil.categoryC_Omissions.size());
        assertEquals(0, DataUtil.categoryCDecreasePatients.get(0).size());
        assertEquals(0, DataUtil.categoryCIncreasePatients.get(0).size());
        assertEquals(0, DataUtil.categoryD_Omissions.size());
        assertEquals(0, DataUtil.categoryDPatients.get(0).size());
        assertEquals(0, DataUtil.categoryE_Omissions.size());
        assertEquals(0, DataUtil.categoryEPatients.get(0).size());

        assertEquals(0, DataUtil.categoryA_Commissions.size());
        assertEquals(0, DataUtil.categoryAPatients.get(1).size());
        assertEquals(0, DataUtil.categoryB_Commissions.size());
        assertEquals(0, DataUtil.categoryBDecreasePatients.get(1).size());
        assertEquals(0, DataUtil.categoryBIncreasePatients.get(1).size());
        assertEquals(0, DataUtil.categoryC_Commissions.size());
        assertEquals(0, DataUtil.categoryCDecreasePatients.get(1).size());
        assertEquals(0, DataUtil.categoryCIncreasePatients.get(1).size());
        assertEquals(0, DataUtil.categoryD_Commissions.size());
        assertEquals(0, DataUtil.categoryDPatients.get(1).size());
        assertEquals(0, DataUtil.categoryE_Commissions.size());
        assertEquals(0, DataUtil.categoryEPatients.get(1).size());

        assertEquals(0, DataUtil.categoryA_Var.size());
        assertEquals(0, DataUtil.categoryAPatients.get(2).size());
        assertEquals(0, DataUtil.categoryB_Var.size());
        assertEquals(0, DataUtil.categoryBDecreasePatients.get(2).size());
        assertEquals(0, DataUtil.categoryBIncreasePatients.get(2).size());
        assertEquals(0, DataUtil.categoryC_Var.size());
        assertEquals(0, DataUtil.categoryCDecreasePatients.get(2).size());
        assertEquals(0, DataUtil.categoryCIncreasePatients.get(2).size());
        assertEquals(0, DataUtil.categoryD_Var.size());
        assertEquals(0, DataUtil.categoryDPatients.get(2).size());
        assertEquals(0, DataUtil.categoryE_Var.size());
        assertEquals(0, DataUtil.categoryEPatients.get(2).size());
    }

}
*/
