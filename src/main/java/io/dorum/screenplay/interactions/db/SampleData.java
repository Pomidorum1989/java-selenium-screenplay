package io.dorum.screenplay.interactions.db;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.ConnectToDB;
import io.dorum.screenplay.interactions.Interaction;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class SampleData implements Interaction<Void> {

    @Override
    public void performAs(Actor actor) {
        String insertDataSQL1 = "INSERT INTO users (name, email) VALUES ('Valentine', 'pomidorum1989@gmail.com')";
        String insertDataSQL2 = "INSERT INTO users (name, email) VALUES ('Michael', 'dorummichael@gmail.com')";

        Connection connection = Actor.getActor().abilityTo(ConnectToDB.class).getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(insertDataSQL1);
            statement.execute(insertDataSQL2);
            log.info("Mock data inserted successfully");
        } catch (SQLException e) {
            log.error("Failed to insert sample data: {}", e.getMessage());
            throw new RuntimeException("Failed to insert sample data", e);
        }
    }

    public static SampleData insertSampleData() {
        return new SampleData();
    }
}
