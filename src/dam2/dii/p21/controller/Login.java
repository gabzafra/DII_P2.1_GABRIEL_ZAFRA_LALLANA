package dam2.dii.p21.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dam2.dii.p21.model.User;
import dam2.dii.p21.service.UserService;


@WebServlet("/login")
public class Login extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Login() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    if (request.getSession().getAttribute("id") != null) {
      request.getSession().setAttribute("id", null);
      request.getSession().setAttribute("name", null);
    }
    response.sendRedirect(request.getContextPath());
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String email = request.getParameter("mail");
    String pass = request.getParameter("pass");



    String error = UserService.validateLogin(email, pass);
    if (error.equals("El usuario no existe.")) {
      User user = new User();
      user.setEmail(email);
      request.setAttribute("user", user);
      request.setAttribute("error", error);
      request.getRequestDispatcher("register.jsp").forward(request, response);
    } else if (error.length() > 0) {
      request.setAttribute("email", email);
      request.setAttribute("password", pass);
      request.setAttribute("error", error);
      request.getRequestDispatcher("login.jsp").forward(request, response);
    } else {
      User user = UserService.getUserByMail(email);
      request.getSession().setAttribute("id", user.getId());
      request.getSession().setAttribute("name", user.getName());
      if (user.isAdmin()) {
        response.sendRedirect("./admin");
      } else {
        response.sendRedirect("./profile");
      }
    }

  }

}
