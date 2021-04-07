package com.gl.procamp.service;

import com.gl.procamp.App;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class SumCalculatorTest {

    public static final double DELTA = 0.01D;
    public static final String FILE_NAME_FIELD_NAME = "FILE_NAME";
    private final String FILE_NAME;

    public SumCalculatorTest() throws NoSuchFieldException, IllegalAccessException {
        FILE_NAME = setFilename();
    }

    /*
     * Java reflection here is used just for practice
     * */
    private String setFilename() throws NoSuchFieldException, IllegalAccessException {
        App app = new App();
        Field privateStringField = App.class.getDeclaredField(FILE_NAME_FIELD_NAME);
        privateStringField.setAccessible(true);
        return (String) privateStringField.get(app);
    }

    @Test(dataProvider = "dataProviderForHappyPath")
    public void testWhenNumbersAreGoodThenGetResult(Double expectedResult, List<String> lines) {
        double actualResult = new SumCalculator(FILE_NAME).sum(lines);
        System.out.println(String.format("Expected result = %s; Actual Result = %s; List = %s", expectedResult, actualResult, lines.toString()));
        Assert.assertEquals(actualResult, expectedResult, DELTA);
    }

    @DataProvider
    public Object[][] dataProviderForHappyPath() {
        return new Object[][]{
                {268.83D, Arrays.asList("# 1000000", "123.4", "-34.21", "56.71",
                        "000", "444.45", "-555.72", "+234.2")},
                {268.83D, Arrays.asList("# 1000000", "  123.4", "-34.21", "56.71",
                        "000  ", "   # iii", "", "444.45", "  -555.72", "+234.2")},
                {222.1D, Arrays.asList("123.1", "222.5", "-123.5")},
                {2.0D, Arrays.asList("  1.1", "+2.2", "-1.3")},
                {-7000000000.0D, Arrays.asList("  1000000000.1", "+2000000000.2", "-10000000000.3")},
                {268.83D, Arrays.asList("# 1000000", "  123.4", "-34.21", "56.71",
                        "000  ", "   # iii", "", "444.45", "  -555.72", "+234.2", " error")},
                {99.0D, Arrays.asList("12_3.1", "222.5", "-123.5")},
        };
    }
}

