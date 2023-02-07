package dam2.dii.p21.service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import dam2.dii.p21.dao.UserDAO;
import dam2.dii.p21.dao.UserDAOinMem;
import dam2.dii.p21.model.User;

public class UserService {
  private static final UserDAO DAO = UserDAOinMem.getInstance();

  public static User getUserById(String id) {
    int parId = parseId(id);
    if (parId > 0) {
      User user = DAO.getUserById(parId);
      user.setPassword("");
      return user;
    } else {
      return null;
    }
  }

  public static User getUserById(int id) {
    if (id > 0) {
      User user = DAO.getUserById(id);
      user.setPassword("");
      return user;
    } else {
      return null;
    }
  }

  public static User getUserByMail(String email) {
    User user = DAO.getAllUsers().stream().filter(u -> u.getEmail().equals(email)).findFirst()
        .orElse(new User());
    user.setPassword("");
    return user;
  }

  public static List<User> getNonAdminUsers() {
    List<User> userList = DAO.getAllUsers();
    return userList = userList.stream().filter(user -> !user.isAdmin()).map(user -> {
      user.setPassword("");
      return user;
    }).collect(Collectors.toList());
  }

  public static List<User> getAllUsers() {
    List<User> userList = DAO.getAllUsers();
    userList.forEach(user -> user.setPassword(""));
    return userList;
  }

  public static List<User> getUsersFilteredByStart(String str) {
    return getNonAdminUsers().parallelStream()
        .filter(user -> user.getName().toLowerCase().startsWith(str.trim().toLowerCase()))
        .collect(Collectors.toList());
  }

  public static boolean deleteUserById(String id) {
    int parId = parseId(id);
    return parId > 0 ? DAO.deleteUserById(parId) : false;
  }

  public static User createUser(User user) {
    boolean emailAlrdyExists =
        !DAO.getAllUsers().stream().noneMatch(item -> item.getEmail().equals(user.getEmail()));
    if (emailAlrdyExists) {
      return new User();
    } else {
      User newUser = DAO.createUser(user);
      newUser.setPassword("");
      return newUser;
    }

  }

  public static User updateUser(User user) {
    if (DAO.getUserById(user.getId()) != null) {
      User updUser = DAO.updateUser(user);
      updUser.setPassword("");
      return updUser;
    } else {
      return user;
    }
  }

  private static boolean logUserIn(int id, String pass) {
    return DAO.getUserById(id).getPassword().equals(pass);
  }

  public static int parseId(String id) {
    int result;
    try {
      result = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      result = -1;
    }
    return result;
  }

  public static boolean hasEmptyFields(User user) {
    return user.getName().equals("") || user.getSurnames().equals("") || user.getEmail().equals("")
        || user.getPhone().equals("") || user.getPassword().equals("");
  }

  public static String validateLogin(String email, String pass) {
    String error = "";
    if (!isValidEmail(email)) {
      error = "El email no es valido.";
    } else {
      int userId = getUserByMail(email).getId();
      if (userId <= 0) {
        error = "El usuario no existe.";
      } else if (!logUserIn(userId, pass)) {
        error = "El password no es valido.";
      }
    }
    return error;
  }

  public static String validateRegister(User user, String pass2) {
    String error = "";
    if (!user.getPassword().equals(pass2)) {
      error = "Los passwords no coinciden.";
    } else if (hasEmptyFields(user)) {
      error = "No se pueden dejar campos en blanco.";
    } else if (!isValidEmail(user.getEmail())) {
      error = "El email no es valido.";
    } else if (getUserByMail(user.getEmail()).getId() > 0) {
      error = "Ya existe un usuario con ese email.";
    } else if (createUser(user).getId() <= 0) {
      error = "Se ha producido un error al crear su usuario.";
    } else {
      error = "";
    }
    return error;
  }

  public static String validateUpdateFields(User userData, int idAuth) {
    String error = "";
    if (userData.getName().equals("") || userData.getSurnames().equals("")
        || userData.getEmail().equals("") || userData.getPhone().equals("")) {
      error = "No puede haber campos en blanco.";
    } else if (!isValidEmail(userData.getEmail())) {
      error = "El email no es valido";
    } else {
      String oldMail = getUserById(userData.getId()).getEmail();
      String newEmail = userData.getEmail();
      if (!oldMail.equals(newEmail) && getUserByMail(newEmail).getId() > 0) {
        error = "El email ya esta en uso";
      } else {
        User authUser = getUserById(idAuth);
        if (authUser.getId() == userData.getId() || authUser.isAdmin()) {
          User oldUser = DAO.getUserById(userData.getId());
          oldUser.setName(userData.getName());
          oldUser.setEmail(userData.getEmail());
          oldUser.setSurnames(userData.getSurnames());
          oldUser.setPhone(userData.getPhone());
          if (updateUser(oldUser).getId() <= 0) {
            error = "No se ha podido realizar la actualización.";
          }
        } else {
          error = "No esta autorizado a realizar esta operación";
        }
      }
    }

    return error;
  }

  public static String validateDeleteUser(User user, int idAuth) {
    String error = "";
    if (getUserById(idAuth).isAdmin()) {
      error = deleteUserById(user.getId() + "") ? "" : "No se ha podido eliminar el usuario.";
    }
    return error;
  }

  public static String changePass(int idAuth, String newPass, String oldPass) {
    String error = "";
    if (newPass.length() <= 0 || oldPass.length() <= 0) {
      error = "El password no puede estar en blanco";
    } else {
      User user = DAO.getUserById(idAuth);
      if (user.getId() <= 0) {
        error = "No existe el usuario.";
      } else if (!user.getPassword().equals(oldPass)) {
        error = "Password erroneo.";
      } else {
        user.setPassword(newPass);
        if (updateUser(user).getId() <= 0) {
          error = "No se ha podido realizar la actualización.";
        }
      }
    }
    return error;
  }


  public static boolean isValidEmail(String mail) {
    String regex =
        "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    return Pattern.compile(regex).matcher(mail).matches();
  }
}
