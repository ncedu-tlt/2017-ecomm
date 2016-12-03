package ru.ncedu.ecomm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class Configuration {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("config.properties");
            if (inputStream == null) throw new NullPointerException("inputStream!");
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("FATAL! Cannot read config.properties!");
        }
    }

    public static String getProperty(String name) {
        return properties.getProperty(name);
    }
}
