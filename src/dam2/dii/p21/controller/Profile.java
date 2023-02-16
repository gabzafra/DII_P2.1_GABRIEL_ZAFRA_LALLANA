package dam2.dii.p21.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import dam2.dii.p21.model.User;
import dam2.dii.p21.service.ConfigService;
import dam2.dii.p21.service.LangService;
import dam2.dii.p21.service.UserService;

@WebServlet("/profile")
public class Profile extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ConfigService appConfig;
  private LangService langService;

  public Profile() {
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

    Integer idAuth = (Integer) request.getSession().getAttribute("id");
    if (idAuth != null) {
      // esta logado
      if (request.getParameter("upd") != null) {
        // update
        User user = UserService.getUserById(request.getParameter("upd"));
        if (user.getId() == idAuth || UserService.getUserById(idAuth).isAdmin()) {
          request.setAttribute("user", user);
          request.getRequestDispatcher("update-profile.jsp").forward(request, response);
        } else {
          response.sendError(404);
        }

      } else {
        // datos desde la sesion
        User user = UserService.getUserById(idAuth);
        if (user.getId() > 0) {
          request.setAttribute("detail", user);
          if (user.isAdmin()) {
            response.sendRedirect("./admin");
          } else {
            request.getRequestDispatcher("profile.jsp").forward(request, response);
          }
        } else {
          response.sendError(404);
        }
      }
    } else {
      // No esta logado
      response.sendError(404);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    initConfig(request);

    Integer idAuth = (Integer) request.getSession().getAttribute("id");
    if (idAuth != null) {
      if (request.getParameter("update") != null) {
        User user = new User();
        int userId = UserService.parseId(request.getParameter("update"));
        user.setId(userId);
        user.setName(request.getParameter("name"));
        user.setSurnames(request.getParameter("surnames"));
        user.setEmail(request.getParameter("mail"));
        user.setPhone(request.getParameter("phone"));

        String error = UserService.validateUpdateFields(user, idAuth);

        if (error.length() > 0) {
          request.setAttribute("user", user);
          error = LangService.getLocalError(idAuth, error);
          request.setAttribute("error", error);
          request.getRequestDispatcher("update-profile.jsp").forward(request, response);
        } else {
          response.sendRedirect(request.getContextPath() + "/profile");
        }

      } else if (request.getParameter("pass") != null) {
        String pass1 = request.getParameter("new-pass1");
        String pass2 = request.getParameter("new-pass2");
        if (!pass1.equals(pass2)) {
          String error = LangService.getLocalError(idAuth, "err_not_same_pw");
          Logger.getLogger("generic").error(error);
          request.setAttribute("error", error);
          request.getRequestDispatcher("change-pass.jsp").forward(request, response);
        } else {
          String oldPass = request.getParameter("old-pass");
          String error = UserService.changePass(idAuth, pass1, oldPass);
          if (error.length() > 0) {
            error = LangService.getLocalError(idAuth, error);
            request.setAttribute("error", error);
            request.getRequestDispatcher("change-pass.jsp").forward(request, response);
          } else {
            response.sendRedirect(request.getContextPath() + "/profile");
          }
        }
      } else {
        response.sendError(404);
      }
    } else {
      response.sendError(404);
    }
  }

}
