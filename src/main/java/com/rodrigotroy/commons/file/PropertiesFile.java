package com.rodrigotroy.commons.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesFile {
    private static final Logger LOG = LogManager.getLogger(PropertiesFileReader.class);
    private final InputStream inputStream;
    private Properties properties;

    public PropertiesFile(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.error(e.getMessage());
                }
            }
        }

        return properties;
    }

    public String getProperty(String key) {
        if (properties == null) {
            this.properties = this.loadProperties();
        }

        return this.properties.getProperty(key);
    }
}
