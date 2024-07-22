package reportportal.listener;

import com.epam.reportportal.listeners.ListenerParameters;
import com.epam.reportportal.service.Launch;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.testng.TestNGService;
import com.epam.reportportal.utils.properties.PropertiesLoader;
import com.epam.ta.reportportal.ws.model.launch.StartLaunchRQ;
import org.testng.util.Strings;
import utils.Config;

import java.util.Calendar;
import java.util.function.Supplier;

public class ParamOverrideTestNgService extends TestNGService {
    public ParamOverrideTestNgService() {
        super(getLaunchOverriddenProperties());
    }

    private static Supplier<Launch> getLaunchOverriddenProperties() {
        ListenerParameters parameters = new ListenerParameters(PropertiesLoader.load());
        parameters.setApiKey(Config.getRpAPIKey());
        ReportPortal reportPortal = ReportPortal.builder().withParameters(parameters).build();
        StartLaunchRQ rq = buildStartLaunch(reportPortal.getParameters());
        return () -> reportPortal.newLaunch(rq);
    }

    private static StartLaunchRQ buildStartLaunch(ListenerParameters parameters) {
        StartLaunchRQ rq = new StartLaunchRQ();
        rq.setName(parameters.getLaunchName());
        rq.setStartTime(Calendar.getInstance().getTime());
        rq.setAttributes(parameters.getAttributes());
        rq.setMode(parameters.getLaunchRunningMode());
        if (!Strings.isNullOrEmpty(parameters.getDescription())) {
            rq.setDescription(parameters.getDescription());
        }
        return rq;
    }
}
