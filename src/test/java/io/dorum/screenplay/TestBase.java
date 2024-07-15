package io.dorum.screenplay;

import io.dorum.screenplay.abilities.BrowseTheWeb;
import io.dorum.screenplay.abilities.DatabaseConnection;
import io.dorum.screenplay.tasks.CloseBrowser;
import org.apache.logging.log4j.ThreadContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ThreadLocalWebDriver;

import java.sql.SQLException;

public class TestBase {
    protected ThreadLocal<Actor> actorThreadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void beforeMethod() throws SQLException {
        ThreadContext.put("threadName", String.valueOf(Thread.currentThread().threadId()));
        actorThreadLocal.set(new Actor("Tester"));
        getActor().can(BrowseTheWeb.with(ThreadLocalWebDriver.getDriver()));
//        getActor().can(new DatabaseConnection("", "", ""));
    }

    @AfterMethod
    public void afterMethod() {
        getActor().attemptsTo(CloseBrowser.permanently());
        actorThreadLocal.remove();
        ThreadContext.clearAll();
    }

    protected Actor getActor() {
        return actorThreadLocal.get();
    }
}
