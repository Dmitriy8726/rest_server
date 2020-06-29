package net.proselyte.jwtappdemo.rest;

import net.proselyte.jwtappdemo.dto.ClientDto;
import net.proselyte.jwtappdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/client/")
public class ClientController {
    private final UserService userService;


    @Autowired
    public ClientController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("viewAll")
    public ResponseEntity<List> viewAll (@RequestHeader Long user_id){
        //List<Client> clients = userService.getAllClient();
        List<ClientDto> result = ClientDto.fromClients(userService.getAllClient(user_id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("viewAllOrden")
    public ResponseEntity<List> viewAllOrden (@RequestHeader Long user_id){
        List<ClientDto> result = ClientDto.fromClients(userService.getAllClientByUser(user_id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("viewAllDone")
    public ResponseEntity<List> viewAllDone (@RequestHeader Long user_id){
        List<ClientDto> result = ClientDto.fromClients(userService.getAllClientByUserDone(user_id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
