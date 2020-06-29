package net.proselyte.jwtappdemo.service;

import net.proselyte.jwtappdemo.model.Client;
import net.proselyte.jwtappdemo.model.User;

import java.util.List;


public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    //List<Client> getAllClient();

    void addOrden (Long user_id, Long client_id);


    void doneOrden (Long client_id);

    List<Client> getAllClientByUser(Long user_id);

    List<Client> getAllClientByUserDone(Long user_id);

    List<Client> getAllClient(Long user_id);
}
