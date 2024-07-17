package io.dorum.screenplay;

import io.dorum.screenplay.abilities.BrowseTheWeb;
import io.dorum.screenplay.interactions.TakeScreenshot;
import io.dorum.screenplay.tasks.CloseBrowser;
import io.dorum.screenplay.tasks.PerformsInteraction;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.BrowserFactory;
import utils.Config;

import java.lang.reflect.Method;
import java.sql.SQLException;

@Log4j2
public class TestBase {
    protected ThreadLocal<Actor> actorThreadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void beforeMethod(Method method) throws SQLException {
        ThreadContext.put("threadName", String.valueOf(Thread.currentThread().threadId()));
        log.info("Method {} is started", method.getName());
        actorThreadLocal.set(new Actor("Tester"));
        getActor().can(BrowseTheWeb.with(BrowserFactory.createDriver(Config.getBrowser())));
//        getActor().can(new DatabaseConnection("", "", ""));
    }

    @AfterMethod
    public void afterMethod(Method method, ITestResult result) {
        if (!result.isSuccess()) {
            getActor().attemptsTo(PerformsInteraction.interaction(TakeScreenshot.of(method.getName())));
        }
        getActor().attemptsTo(CloseBrowser.permanently());
        actorThreadLocal.remove();
        ThreadContext.clearAll();
        log.info("Method {} is finished", method.getName());
    }

    protected Actor getActor() {
        return actorThreadLocal.get();
    }
}
