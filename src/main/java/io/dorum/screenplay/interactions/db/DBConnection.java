package io.dorum.screenplay.interactions.db;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.ConnectToDB;
import io.dorum.screenplay.interactions.Interaction;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
public class DBConnection implements Interaction<Void> {

    @Override
    public void performAs(Actor actor) {
        Connection connection = Actor.getActor().abilityTo(ConnectToDB.class).getConnection();
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                log.info("DB connection closed");
            }
        } catch (SQLException e) {
            log.error("Failed to close DB connection: {}", e.getMessage());
            throw new RuntimeException("Failed to close DB connection", e);
        }
    }

    public static DBConnection close() {
        return new DBConnection();
    }
}
