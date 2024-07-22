package utils;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class Config {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties("config.properties");
        loadProperties("reportportal.properties");
    }

    private static void loadProperties(String fileName) {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Unable to load " + fileName);
            }
            PROPERTIES.load(input);
        } catch (IOException ex) {
            log.error("Unable to load config file: {}", fileName, ex);
        }
    }

    public static String getUrl() {
        return PROPERTIES.getProperty("url");
    }

    public static String getBrowser() {
        return PROPERTIES.getProperty("browser");
    }

    public static String getDBUser() {
        return PROPERTIES.getProperty("db.user");
    }

    public static String getDBPassword() {
        return PROPERTIES.getProperty("db.password");
    }

    public static String getDBServer() {
        return PROPERTIES.getProperty("db.server");
    }

    public static String getRpAPIKey() {
        return PROPERTIES.getProperty("rp.api.key");
    }
}