package io.dorum.screenplay.interactions.db;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.ConnectToDB;
import io.dorum.screenplay.interactions.Interaction;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class Tables implements Interaction<Void> {

    @Override
    public void performAs(Actor actor) {
        String dropTablesSQL = "DROP ALL OBJECTS";
        Connection connection = Actor.getActor().abilityTo(ConnectToDB.class).getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(dropTablesSQL);
            log.info("All database objects dropped successfully");
        } catch (SQLException e) {
            log.error("Failed to drop all database objects: {}", e.getMessage());
            throw new RuntimeException("Failed to drop all database objects", e);
        }
    }

    public static Tables dropAll() {
        return new Tables();
    }
}
