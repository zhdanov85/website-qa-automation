package com.example.qa.automation.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigReader.class);
    private static ConfigReader instance;
    private Properties properties;
    private final String CONFIG_FILE = "env.properties";

    private ConfigReader() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                logger.error("Sorry, unable to find {}", CONFIG_FILE);
                // Optionally throw an exception or handle this more gracefully
            } else {
                properties.load(input);
                logger.info("Loaded configuration from {}", CONFIG_FILE);
            }
        } catch (IOException ex) {
            logger.error("Error loading configuration from {}: {}", CONFIG_FILE, ex.getMessage());
        }
    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {
            instance = new ConfigReader();
        }
        return instance;
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warn("Property \"{}\" not found in {}. Returning null.", key, CONFIG_FILE);
        }
        return value;
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
