package io.dorum.screenplay.interactions;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.tasks.Task;
import io.dorum.screenplay.abilities.DatabaseConnection;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
@Log4j2
public class ExecuteQuery implements Task {
    private final String query;

    public ExecuteQuery(String query) {
        this.query = query;
    }

    @Override
    public void performAs(Actor actor) {
        DatabaseConnection dbConnection = actor.abilityTo(DatabaseConnection.class);
        try {
            Connection connection = dbConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
        } catch (Exception e) {
            log.error("Unable to execute query");
        }
    }
}
