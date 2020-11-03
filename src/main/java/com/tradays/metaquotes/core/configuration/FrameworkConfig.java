package com.tradays.metaquotes.core.configuration;

import org.aeonbits.owner.ConfigFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author Nikolay Streltsov on 01.11.2020
 */
public class FrameworkConfig {
    private static IFrameworkConfig config = null;

    private FrameworkConfig(){}

    public static IFrameworkConfig get(){
        if (config == null)
            config = load();
        return config;
    }

    public static IFrameworkConfig load() {
        try {
            Properties properties = new Properties();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("environment.properties");
            InputStreamReader inR = new InputStreamReader(in, "windows-1251");
            properties.load(inR);
            return ConfigFactory.create(IFrameworkConfig.class, properties,
                    System.getProperties(), System.getenv());
        } catch (IOException e) {
        }
        return null;
    }
}
