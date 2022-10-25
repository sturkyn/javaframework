package qa.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class PropertiesUtils {
    public static final String PROP_FILE_NAME = "/target/classes/config.properties";
    private static Map<String, String> props = getPropValues();

    public static String getStringValue(String key) {
        return props.get(key);
    }

    public static long getLongValue(String key) {
        return Long.parseLong(props.get(key));
    }

    public static int getIntValue(String key) {
        return Integer.parseInt(props.get(key));
    }

    public static String getProp(String key) {
        props = getPropValues();
        return props.get(key);
    }

    public static Map<String, String> getPropValues() {
        Map<String, String> props = new HashMap<>();
        InputStream inputStream = null;

        try {
            Properties prop = new Properties();

            File currentDirectory = new File(new File(".").getAbsolutePath());
            inputStream = Files.newInputStream(Paths.get(currentDirectory.getCanonicalPath() + PROP_FILE_NAME));

            prop.load(inputStream);

            Set<Object> keys = prop.keySet();
            for (Object key : keys) {
                String k = (String) key;
                props.put(k, prop.getProperty(k));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return props;
    }
}