package reportportal;

import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.util.Base64;

@Log4j2(topic = "binary_data_logger")
public class RPLogUtils {

    public static void log(File file, String message) {
        log.info("RP_MESSAGE#FILE#{}#{}", file.getAbsolutePath(), message);
    }

    public static void logBase64(String base64, String message) {
        log.info("RP_MESSAGE#BASE64#{}#{}", base64, message);
    }

    public static void log(byte[] bytes, String message) {
        logBase64(Base64.getEncoder().encodeToString(bytes), message);
    }
}