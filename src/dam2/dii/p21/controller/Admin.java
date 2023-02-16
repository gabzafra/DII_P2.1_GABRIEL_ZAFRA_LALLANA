package dam2.dii.p21.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dam2.dii.p21.model.User;
import dam2.dii.p21.service.ConfigService;
import dam2.dii.p21.service.LangService;
import dam2.dii.p21.service.UserService;

@WebServlet("/admin")
public class Admin extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private ConfigService appConfig;
  private LangService langService;

  public Admin() {
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
      User user = UserService.getUserById(idAuth);
      if (user.isAdmin()) {
        List<User> userList = UserService.getNonAdminUsers();
        String updId = request.getParameter("upd");
        String delId = request.getParameter("del");
        if (updId == null && delId == null) {
          request.setAttribute("error", null);
          request.setAttribute("list", userList);
          request.setAttribute("detail", user);
          request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else if (updId != null) {
          User updateUser = userList.stream().filter(u -> updId.equals(u.getId() + "")).findFirst()
              .orElse(new User());
          if (updateUser.getId() > 0) {
            request.setAttribute("user", updateUser);
            request.getRequestDispatcher("update-profile.jsp").forward(request, response);
          } else {
            response.sendError(404);
          }
        } else if (delId != null) {
          User targetUser = UserService.getUserById(delId);
          if (request.getParameter("conf") != null) {
            // confirmado delete
            String error = UserService.validateDeleteUser(targetUser, idAuth);
            if (error.length() > 0) {
              error = LangService.getLocalError(idAuth, error);
              request.setAttribute("error", error);
            }
            userList = UserService.getNonAdminUsers();
            request.setAttribute("list", userList);
            request.setAttribute("detail", user);
            request.getRequestDispatcher("admin.jsp").forward(request, response);
          } else {
            // mostrar SI/NO
            request.setAttribute("user", targetUser);
            request.getRequestDispatcher("confirmation.jsp").forward(request, response);
          }
        } else {
          response.sendError(404);
        }
      } else {
        response.sendError(404);
      }
    } else {
      response.sendError(404);
    }

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    initConfig(request);

    String filterStr = request.getParameter("filter");
    Integer idAuth = (Integer) request.getSession().getAttribute("id");
    if (idAuth != null) {
      User user = UserService.getUserById(idAuth);
      if (user.isAdmin()) {
        if (filterStr != null) {
          request.setAttribute("list", UserService.getUsersFilteredByStart(filterStr));
        } else {
          request.setAttribute("list", UserService.getNonAdminUsers());
        }
        request.setAttribute("filter", filterStr);
        request.setAttribute("detail", user);
        request.getRequestDispatcher("admin.jsp").forward(request, response);
      } else {
        response.sendError(404);
      }
    } else {
      response.sendError(404);
    }
  }

}
