package io.dorum.screenplay.questions;

import io.dorum.screenplay.Actor;
import io.dorum.screenplay.abilities.ConnectToDB;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j2
@AllArgsConstructor
public class QueryDB implements Question<String> {
    private final String query;

    @Override
    public String answeredBy(Actor actor) {
        Connection connection = actor.abilityTo(ConnectToDB.class).getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getString(1);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute query", e);
        }
    }

    public static QueryDB withQuery(String query) {
        return new QueryDB(query);
    }

    public static QueryDB selectEmailByName(String name) {
        String query = "SELECT email FROM users WHERE name = '" + name + "'";
        return new QueryDB(query);
    }

}
