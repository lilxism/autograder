package configsettings;

import java.io.FileReader;
import java.util.Properties;

public class ConfigSettings {
    private Properties settings = new Properties();

    public ConfigSettings(String fileName) {
        try {
            settings.load(new FileReader(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public String getSetting(String key) {
        return getSetting(key, null);
    }

    public String getSetting(String key, String defaultValue) {
        return settings.getProperty(key, defaultValue);
    }

    public static void main(String[] args) {
        ConfigSettings test = new ConfigSettings("settings.cfg");
        System.out.println(test.getSetting("CHECK_COMMENT_PER_MODULE"));
    }
}
