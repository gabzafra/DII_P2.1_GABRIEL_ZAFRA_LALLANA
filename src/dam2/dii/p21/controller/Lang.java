package dam2.dii.p21.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dam2.dii.p21.service.ConfigService;

@WebServlet("/lang")
public class Lang extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ConfigService appConfig;

  public Lang() {
    super();
  }

  private void initConfig(HttpServletRequest request) {
    if (appConfig == null) {
      String sysPath =
          request.getServletContext().getRealPath("") + "\\WEB-INF\\classes\\dam2\\dii\\p21\\";

      appConfig = ConfigService.getInstance(sysPath);
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    initConfig(request);

    String sesionLocaleStr = (String) request.getSession().getAttribute("idioma");

    String lang = request.getParameter("set");


    if (sesionLocaleStr == null) {
      sesionLocaleStr = ConfigService.getParametro("app.lang");
    } else if (!sesionLocaleStr.equals(lang)) {
      sesionLocaleStr = lang;
    }

    request.getSession().setAttribute("idioma", sesionLocaleStr);
    response.sendRedirect("index.jsp");
  }

}
