package org.leogenwp.service;

import org.leogenwp.model.User;
import org.leogenwp.repository.UserRepository;
import org.leogenwp.repository.io.JavaIOUserRepository;

import java.util.List;

public class UserService {
    UserRepository userRepository = new JavaIOUserRepository();
    public User save(User u) {
       return userRepository.save(u);
    }
    public User getById(Integer id) {
        User user = userRepository.getById(id);
        return user;
    }
    public List<User> getAll() {
        return userRepository.getall();
    }

    public User getByLogin(String login) {
        User user = userRepository.getByLogin(login);
        return user;
    }

    public User update(User user) {
         user = userRepository.update(user);
         return user;
    }

    public void deleteById(Integer id) {
         userRepository.deleteById(id);
    }


}
