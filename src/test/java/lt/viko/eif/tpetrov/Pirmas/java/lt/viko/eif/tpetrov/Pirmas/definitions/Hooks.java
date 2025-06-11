package lt.viko.eif.tpetrov.Pirmas.definitions;

import io.cucumber.java.After;
import lt.viko.eif.tpetrov.Pirmas.helper.DriverHelper;

public class Hooks {
    @After
    public void tearDown() {
        if (DriverHelper.driver != null) {
            DriverHelper.driver.quit();
        }
    }
}

