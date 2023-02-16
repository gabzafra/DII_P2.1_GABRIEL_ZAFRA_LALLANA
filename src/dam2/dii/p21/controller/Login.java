package dam2.dii.p21.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dam2.dii.p21.model.User;
import dam2.dii.p21.service.ConfigService;
import dam2.dii.p21.service.LangService;
import dam2.dii.p21.service.UserService;


@WebServlet("/login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ConfigService appConfig;
  private LangService langService;

  public Login() {
    super();
  }

  private void initConfig(HttpServletRequest request) {
    if (appConfig == null) {
      String sysPath =
          request.getServletContext().getRealPath("") + "\\WEB-INF\\classes\\dam2\\dii\\p21\\";

      appConfig = ConfigService.getInstance(sysPath);
      langService = LangService.getInstance(appConfig);
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    initConfig(request);

    if (request.getSession().getAttribute("id") != null) {
      request.getSession().setAttribute("id", null);
      request.getSession().setAttribute("name", null);
    }
    response.sendRedirect("index.jsp");
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    initConfig(request);

    String email = request.getParameter("mail");
    String pass = request.getParameter("pass");
    String lang = (String) request.getSession().getAttribute("idioma");

    String error = UserService.validateLogin(email, pass, lang);

    if (error.equals("El usuario no existe.")) {
      User user = new User();
      user.setEmail(email);
      request.setAttribute("user", user);
      error = langService.getLocalError(lang, error);
      request.setAttribute("error", error);
      request.getRequestDispatcher("register.jsp").forward(request, response);
    } else if (error.length() > 0) {
      request.setAttribute("email", email);
      request.setAttribute("password", pass);
      error = langService.getLocalError(lang, error);
      request.setAttribute("error", error);
      request.getRequestDispatcher("login.jsp").forward(request, response);
    } else {
      User user = UserService.getUserByMail(email);
      request.getSession().setAttribute("id", user.getId());
      request.getSession().setAttribute("idioma", user.getLang());
      request.getSession().setAttribute("name", user.getName());
      if (user.isAdmin()) {
        response.sendRedirect("./admin");
      } else {
        response.sendRedirect("./profile");
      }
    }

  }

}
