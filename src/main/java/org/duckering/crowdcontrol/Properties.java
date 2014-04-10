package org.duckering.crowdcontrol;

import com.esotericsoftware.yamlbeans.YamlReader;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: tomduckering
 * Date: 25/04/2013
 * Time: 09:48
 * To change this template use File | Settings | File Templates.
 */
public class Properties {

    public static final String SYSTME_PROPERTY_KEY_CONFIG = "config";
    public static final String DEFAULT_CONFIG_FILE_PATH = "./config.yaml";

    public static final String PORT_YAML_KEY = "port";
    public static final String WAR_PATH_YAML_KEY = "warPath";
    public static final String CROWD_HOME_YAML_KEY = "crowdHome";

    public final Map<String,Object> properties;

    public Properties() throws Exception {
        String configFilePath = System.getProperty(SYSTME_PROPERTY_KEY_CONFIG, DEFAULT_CONFIG_FILE_PATH);

        File configFile = new File(configFilePath);

        if (! configFile.exists()) {
            throw new Exception ("Can't load config file from " + configFile.getAbsolutePath());
        }

        YamlReader reader = new YamlReader(new FileReader(configFile));
        properties = (Map)(reader.read());
    }

    public Object get(String key) {
        return properties.get(key);
    }

    public int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    public String getString(String key) {
        return (String)get(key);
    }

    public Map<String,Object> getMap(String key) {
        return (Map<String,Object>)get(key);
    }

    //Specific getters

    public int port() {
        return getInt(PORT_YAML_KEY);
    }

    public String warPath() {
        return getString(WAR_PATH_YAML_KEY);
    }

    public String crowdHome() {
        return getString(CROWD_HOME_YAML_KEY);
    }
}
