package dam2.dii.p21.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import dam2.dii.p21.service.ConfigService;

public class ConectorJDBC {

  private Connection dbConnection;

  public ConectorJDBC() {
    dbConnection = createConnection();
  }


  private Connection createConnection() {
    Connection dbConection = null;

    try {
      String bd = ConfigService.getParametro("bbdd.nombre");
      String login = ConfigService.getParametro("bbdd.login");
      String password = ConfigService.getParametro("bbdd.pass");
      String host = ConfigService.getParametro("bbdd.host");
      String driver = ConfigService.getParametro("bbdd.driver");
      String url = ConfigService.getParametro("bbdd.url");

      Class.forName(driver);
      dbConection = DriverManager.getConnection(url + host + "/" + bd, login, password);

      dbConection.setAutoCommit(false);
    } catch (Exception e) {
      System.out.println(e);
      dbConection = null;
    }
    return dbConection;
  }

  public Connection getConnection() {
    return this.dbConnection;
  }

  public boolean endConnection() {
    try {
      dbConnection.close();
      dbConnection = null;
      return true;
    } catch (SQLException e) {
      return false;
    }
  }

}
