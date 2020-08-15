package com.vic.decoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigHandler {
    private static final String INPUT_PROP = "inputFileName";
    private static final String CONFIG_PROPERTIES = "config.properties";

    public static String getSource() {
        String inputFileName = getProps().getProperty(INPUT_PROP);
        if (inputFileName == null) {
            throw new RuntimeException(INPUT_PROP + " не найден");
        }

        return inputFileName;
    }

    private static Properties getProps() {
        Properties props = new Properties();
        try (InputStream is = ConfigHandler.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
            if (is != null) {
                props.load(is);
            } else {
                throw new RuntimeException("Ошибка загрузки файла конфигурации");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props;
    }
}
