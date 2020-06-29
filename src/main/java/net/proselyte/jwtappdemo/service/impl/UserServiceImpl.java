package net.proselyte.jwtappdemo.service.impl;


import lombok.extern.slf4j.Slf4j;
import net.proselyte.jwtappdemo.model.*;

import net.proselyte.jwtappdemo.repository.ClientRepository;
import net.proselyte.jwtappdemo.repository.ClientUserRepository;
import net.proselyte.jwtappdemo.repository.RoleRepository;
import net.proselyte.jwtappdemo.repository.UserRepository;
import net.proselyte.jwtappdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final ClientUserRepository clientUserRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder, ClientRepository clientRepository, ClientUserRepository clientUserRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
        this.clientUserRepository = clientUserRepository;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);


        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - {} users found", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            return null;
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id: {} successfully deleted");
    }

    @Override
    public List<Client> getAllClient(Long user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        if (user == null) {
            return null;
        }
        List<Client> result = clientRepository.findAllByStatusAndByCity(Status.ACTIVE, user.getCity());
        return result;
    }

    @Override
    public void addOrden (Long user_id, Long client_id) {
        Client client = new Client();
        client = clientRepository.findById(client_id).orElse(null);
        if (client == null) {
            return;
        }
        if (!clientUserRepository.existsByUser_idAndByClient_id(user_id, client_id)) {
            client.setStatus(Status.BUSY);
            clientRepository.save(client);

            ClientUser clientUser = new ClientUser();
            clientUser.setUser_id(user_id);
            clientUser.setClient_id(client_id);
            clientUserRepository.save(clientUser);
        } else if (client.getStatus() == Status.ACTIVE) {
            client.setStatus(Status.BUSY);
            clientRepository.save(client);
        }
    }


    @Override
    public void doneOrden (Long client_id) {
        Client client = clientRepository.findById(client_id).orElse(null);
        if (client == null) {
            return;
        }
        client.setStatus(Status.DONE);
        clientRepository.save(client);
    }


    @Override
    public List<Client> getAllClientByUser(Long user_id) {
        List<ClientUser> clientUsers = clientUserRepository.findAllByUser_Id(user_id);
        ArrayList<Client> result = new ArrayList<>();
        for (ClientUser clientUser: clientUsers) {
            Client client = new Client();
            client = clientRepository.findById(clientUser.getClient_id()).orElse(null);
            if (client.getStatus() == Status.BUSY) {
                result.add(client);
            }
        }
        return result;
    }



    @Override
    public List<Client> getAllClientByUserDone(Long user_id) {
        List<ClientUser> clientUsers = clientUserRepository.findAllByUser_Id(user_id);
        ArrayList<Client> result = new ArrayList<>();
        for (ClientUser clientUser: clientUsers) {
            Client client = new Client();
            client = clientRepository.findById(clientUser.getClient_id()).orElse(null);
            if (client.getStatus() == Status.DONE) {
                result.add(client);
            }
        }
        return result;
    }
}