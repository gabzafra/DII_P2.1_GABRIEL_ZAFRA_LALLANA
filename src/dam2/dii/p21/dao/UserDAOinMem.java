package dam2.dii.p21.dao;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import dam2.dii.p21.model.User;

public class UserDAOinMem implements UserDAO {
  private static UserDAOinMem instance;
  private HashMap<Integer, User> userList;
  private int id;

  private UserDAOinMem() {
    userList = new HashMap<Integer, User>();
    id = 0;

    createUser(new User("Admin", "The Admin", "admin@mail.com", "000000000", "admin", true));
    createUser(new User("Adam", "Alda Almirante", "adam@mail.com", "666554433", "aaaa", false));
    createUser(new User("Betty", "Bueno Baños", "betty@mail.com", "555443322", "bbbb", false));
    createUser(new User("Charlie", "Corral Casar", "charlie@mail.com", "444332211", "cccc", false));
    createUser(new User("Cecil", "Cinta Coso", "cecil@mail.com", "420332811", "cece", false));
    createUser(new User("Diane", "Dueñas Donoso", "diane@mail.com", "333221100", "dddd", false));
    createUser(new User("Eric", "Estepa España", "eric@mail.com", "111223344", "eeee", false));
  }

  public static UserDAOinMem getInstance() {
    if (instance == null) {
      instance = new UserDAOinMem();
    }
    return instance;
  }

  private int getNewId() {
    id++;
    return id;
  }

  private static User cloneUser(User c) {
    return new User(c.getId(), c.getName(), c.getSurnames(), c.getEmail(), c.getPhone(),
        c.getPassword(), c.isAdmin());
  }

  @Override
  public User createUser(User contact) {
    contact.setId(getNewId());
    if (!userList.containsKey(contact.getId())) {
      userList.put(contact.getId(), contact);
      return cloneUser(userList.get(contact.getId()));
    } else {
      return new User();
    }
  }

  @Override
  public List<User> getAllUsers() {
    return userList.values().stream().map(UserDAOinMem::cloneUser).collect(Collectors.toList());
  }

  @Override
  public User getUserById(int id) {
    User foundUser = userList.get(id);
    return foundUser != null ? cloneUser(foundUser) : new User();
  }

  @Override
  public boolean deleteUserById(int id) {
    return userList.remove(id) != null;
  }

  @Override
  public User updateUser(User contact) {
    userList.replace(contact.getId(), contact);
    User updatedUser = getUserById(contact.getId());
    return updatedUser != null ? cloneUser(updatedUser) : new User();
  }
}
