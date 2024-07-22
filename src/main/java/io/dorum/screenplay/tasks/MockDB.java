package io.dorum.screenplay.tasks;

import com.epam.reportportal.annotations.Step;
import io.dorum.screenplay.Actor;
import io.dorum.screenplay.interactions.db.DBSchema;
import io.dorum.screenplay.interactions.db.SampleData;

public class MockDB implements Task<Void> {
    @Override
    @Step("Creating data base")
    public void performAs(Actor actor) {
        Actor.getActor().interactsWith(DBSchema.createDBSchema());
        Actor.getActor().interactsWith(SampleData.insertSampleData());
    }

    public static MockDB createMockDB() {
        return new MockDB();
    }
}
