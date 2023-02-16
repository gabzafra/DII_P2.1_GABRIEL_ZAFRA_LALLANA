package dam2.dii.p21.service;

import java.util.Locale;
import java.util.ResourceBundle;
import dam2.dii.p21.dao.UserDAO;
import dam2.dii.p21.dao.UserDAOinMem;
import dam2.dii.p21.model.User;

public class LangService {
  private static LangService instance;
  private static final UserDAO DAO = UserDAOinMem.getInstance();
  private static ConfigService Conf;

  private LangService(ConfigService conf) {
    Conf = conf;
  }

  public static LangService getInstance(ConfigService conf) {
    if (instance == null) {
      instance = new LangService(conf);
    }
    return instance;
  }

  public static String getLocalError(String error) {
    return getLocalError(Conf.getParametro("app.lang"), error);
  }

  public static String getLocalError(int userId, String errorMsg) {
    User user = DAO.getUserById(userId);

    ResourceBundle rb;
    if (user.getId() > 0) {
      rb = ResourceBundle.getBundle("idioma", new Locale(user.getLang()));
    } else {
      String defaultLang = Conf.getParametro("app.lang");
      rb = ResourceBundle.getBundle("idioma", new Locale(defaultLang));
    }

    return rb.getString(errorMsg);
  }

  public static String getLocalError(String localStub, String errorMsg) {
    ResourceBundle rb = ResourceBundle.getBundle("idioma", new Locale(localStub));
    return rb.getString(errorMsg);
  }
}
