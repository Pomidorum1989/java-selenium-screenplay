package io.dorum.screenplay.abilities;

import com.epam.reportportal.annotations.Step;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j2
@Getter
public class ConnectToDB implements Ability {

    private final Connection connection;

    @Step("DB creation {url}")
    public static ConnectToDB with(String url)  {
        return new ConnectToDB(url);
    }

    private ConnectToDB(String url) {
        Connection tempConnection = null;
        try {
            tempConnection = DriverManager.getConnection(url);
            log.info("DB connection established: {}", url);
            log.info("DB client info: {}",tempConnection.getClientInfo());
            log.info("DB schema: {}",tempConnection.getSchema());
        } catch (SQLException e) {
            log.error("Unable to establish DB connection:\n {}", e.getMessage());
            throw new RuntimeException("Failed to establish DB connection", e);
        } finally {
            this.connection = tempConnection;
        }
    }
}