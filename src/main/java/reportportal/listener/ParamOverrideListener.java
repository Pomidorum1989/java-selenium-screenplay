package reportportal.listener;

import com.epam.reportportal.testng.BaseTestNGListener;

public class ParamOverrideListener extends BaseTestNGListener {
    public ParamOverrideListener() {
        super(new ParamOverrideTestNgService());
    }
}

