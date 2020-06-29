package net.proselyte.jwtappdemo.rest;


import net.proselyte.jwtappdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/order/")
public class OrdenController {
    private final UserService userService;


    @Autowired
    public OrdenController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("add")
    public ResponseEntity<List> add (@RequestHeader Long user_id, @RequestHeader Long client_id){
        userService.addOrden(user_id, client_id);
        return new ResponseEntity<>(new ArrayList(), HttpStatus.OK);
    }


    @PostMapping("done")
    public ResponseEntity<List> done (@RequestHeader Long client_id){
        userService.doneOrden(client_id);
        return new ResponseEntity<>(new ArrayList(), HttpStatus.OK);
    }

}
