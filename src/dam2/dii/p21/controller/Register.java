package dam2.dii.p21.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dam2.dii.p21.model.User;
import dam2.dii.p21.service.UserService;

@WebServlet("/register")
public class Register extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public Register() {
    super();
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    User user = new User();
    user.setName(request.getParameter("name"));
    user.setSurnames(request.getParameter("surnames"));
    user.setEmail(request.getParameter("mail"));
    user.setPhone(request.getParameter("phone"));
    user.setPassword(request.getParameter("pass1"));
    String pass2 = request.getParameter("pass2");

    String error = UserService.validateRegister(user, pass2);

    if (error.length() > 0) {
      request.setAttribute("error", error);
      request.setAttribute("user", user);
      request.setAttribute("pass2", pass2);
      request.getRequestDispatcher("register.jsp").forward(request, response);
    } else {
      response.sendRedirect("login.jsp");
    }
  }

}
