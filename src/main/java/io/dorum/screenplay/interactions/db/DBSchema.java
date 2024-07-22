package io.dorum.screenplay.interactions.db;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.ConnectToDB;
import io.dorum.screenplay.interactions.Interaction;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
public class DBSchema implements Interaction<Void> {

    @Override
    public void performAs(Actor actor) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "email VARCHAR(255) NOT NULL UNIQUE" +
                ")";

        Connection connection = Actor.getActor().abilityTo(ConnectToDB.class).getConnection();

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            log.info("Schema created successfully");
        } catch (SQLException e) {
            log.error("Failed to create schema: {}", e.getMessage());
            throw new RuntimeException("Failed to create schema", e);
        }
    }

    public static DBSchema createDBSchema() {
        return new DBSchema();
    }
}
