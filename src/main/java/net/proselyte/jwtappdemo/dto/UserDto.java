package net.proselyte.jwtappdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import net.proselyte.jwtappdemo.model.User;

import java.util.*;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String username;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setUsername(username);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());

        return userDto;
    }

    public static List <UserDto> fromUsers(List<User> users) {

        ArrayList<UserDto> usdto = new ArrayList<>();
        for (User user: users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setUsername(user.getUsername());
            usdto.add(userDto);
        }
        return usdto;
    }

    public Long getId() {
        return this.id;
    }
}
