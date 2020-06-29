package net.proselyte.jwtappdemo.rest;


import net.proselyte.jwtappdemo.dto.UserDto;
import net.proselyte.jwtappdemo.model.User;
import net.proselyte.jwtappdemo.security.jwt.JwtTokenProvider;
import net.proselyte.jwtappdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping(value = "/auth/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestHeader String username, @RequestHeader String password) {
        try {

            //$2a$04$3Kng7/mPr/N65JvbkYhXZ.eyPwSa7.l/leF/mPIK3P2FL5hbURCrC
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            UserDto userDto = UserDto.fromUser(user);
            response.put("username", username);
            response.put("token", token);
            response.put("id", userDto.getId());
            System.out.println(token.toString());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
