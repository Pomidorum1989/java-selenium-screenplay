package io.dorum.screenplay;

import io.dorum.screenplay.abilities.ConnectToDB;
import io.dorum.screenplay.abilities.CreateDriver;
import io.dorum.screenplay.interactions.Browser;
import io.dorum.screenplay.interactions.db.DBConnection;
import io.dorum.screenplay.interactions.db.Tables;
import io.dorum.screenplay.interactions.webelements.TakeScreenshot;
import io.dorum.screenplay.tasks.MockDB;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.Config;

import java.lang.reflect.Method;
import java.util.UUID;

@Log4j2
public class TestBase { ;

    @BeforeMethod
    public void beforeMethod(Method method) {
        ThreadContext.put("threadName", String.valueOf(Thread.currentThread().threadId()));
        log.info("Method {} is started", method.getName());
        Actor.createActor("Tester");
        Actor.getActor().can(CreateDriver.of(Config.getBrowser()));
        Actor.getActor().can(ConnectToDB.with(String.format(Config.getDBServer(), UUID.randomUUID())));
        Actor.getActor().attemptsTo(MockDB.createMockDB());
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method method, ITestResult result) {
        if (!result.isSuccess()) {
            Actor.getActor().interactsWith(TakeScreenshot.of(method.getName()));
        }
        Actor.getActor().interactsWith(Browser.close()).interactsWith(Tables.dropAll()).interactsWith(DBConnection.close());
        Actor.removeActor();
        ThreadContext.clearAll();
        log.info("Method {} is finished\n", method.getName());
    }
}
