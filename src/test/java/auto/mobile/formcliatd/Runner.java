package auto.mobile.formcliatd;

import com.appium.manager.ATDRunner;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    @Test
    public void testApp() throws Exception {
        ATDRunner parallelThread = new ATDRunner();
        List<String> tests = new ArrayList<>();
        tests.add("HomeScreenTest");
//        tests.add("PersonalTabTest");

        boolean runner = parallelThread.runner("auto.mobile.formcliatd.specs", tests);
        Assert.assertFalse(runner);
    }
}
