package dam2.dii.p21.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ConfigService {

  private static HashMap<String, PropBundle> propList = new HashMap<String, PropBundle>();
  private static HashMap<String, String> propertyMap = new HashMap<String, String>();
  private static ConfigService instance;

  private ConfigService(String sysPath) {
    List<File> propFilesList = getPropFiles(sysPath + "properties\\");

    propFilesList.forEach(propFile -> {
      PropBundle prop = new PropBundle(propFile);
      try (FileInputStream fis = new FileInputStream(propFile)) {
        prop.load(fis);
      } catch (Exception e) {
        System.out.println("NO existe el fichero de propiedades");
        prop = null;
      }
      if (prop != null) {
        propList.put(prop.getAlias(), prop);
      }
    });

    initLogger(sysPath);
  }

  public static ConfigService getInstance(String sysPath) {
    if (instance == null) {
      instance = new ConfigService(sysPath);
    }
    return instance;
  }


  private void initLogger(String sysPath) {
    String logPropsPath = sysPath + "properties\\" + getParametro("paths.log") + ".properties";
    Properties props = new Properties();
    // Set server log file location
    try (FileInputStream fis = new FileInputStream(logPropsPath)) {
      props.load(fis);
      props.setProperty("log4j.appender.file.File", sysPath + "logs\\actions.log");
      PropertyConfigurator.configure(props);
      Logger.getLogger("generic")
          .warn("Path del archivo de log -> " + props.getProperty("log4j.appender.file.File"));
    } catch (Exception e) {
      System.out.println("No se encuentra el archivo de configuración de logs");
      System.out.println("Logger en configuración por defecto.");
      BasicConfigurator.configure();
    }
  }

  private static List<File> getPropFiles(String pathFile) {
    Properties pathProps = new Properties();

    try (FileInputStream fis = new FileInputStream(pathFile + "paths.properties")) {
      pathProps.load(fis);
    } catch (Exception e) {
      pathProps = null;
    }

    return pathProps.values().stream()
        .map(propValue -> new File(pathFile + propValue + ".properties"))
        .collect(Collectors.toList());
  }

  private static String getAlias(String propKey) {
    return propKey.split("\\.")[0];
  }

  public static String getParametro(String propertyKey) {

    String propertyValue = null;
    if (propertyMap.containsKey(propertyKey)) {
      propertyValue = propertyMap.get(propertyKey);
    } else {
      String propAlias = getAlias(propertyKey);
      propertyValue = propList.get(propAlias).getProperty(propertyKey);
      if (propertyValue == null) {
        System.out
            .println("El parametro <" + propertyKey + "> NO existe en el fichero de propiedades");
      } else {
        propertyMap.put(propertyKey, propertyValue);
      }
    }
    return propertyValue;
  }

  public static boolean setProperty(String key, String value) {
    PropBundle props = propList.get(getAlias(key));
    if (props != null) {
      props.setProperty(key, value);// Actualiza el bundle
      propertyMap.put(key, value);// Actualiza la cache
      // Intenta guardar en disco el bundle
      return storePropsBundle(props);
    } else {
      return false;
    }
  }

  private static boolean storePropsBundle(PropBundle props) {
    try (FileOutputStream fos = new FileOutputStream(props.getPropFile())) {
      props.store(fos, "desarrollo");
    } catch (Exception e) {
      return false;
    }
    return true;
  }


}
